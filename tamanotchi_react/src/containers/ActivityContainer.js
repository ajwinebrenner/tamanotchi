import { useEffect, useState } from "react";
import House from "../components/House";
import Food from "../components/Food";
import MiniGame from "../components/MiniGame";

const ActivityContainer = ({pet, feedPet, upgradeHouse, wonGame}) => {

    // what to display intially
    const [showHouse, setShowHouse] = useState(true);
    const [showShop, setShowShop] = useState(false);
    const [showGame, setShowGame] = useState(false);


    // function that turns all buttons to their opposite

    const handleShowHouseClick = () => {
        setShowShop(false)
        setShowGame(false)
        setShowHouse(true)

    }

    const handleShowShopClick = () => {
        setShowShop(true)
        setShowGame(false)
        setShowHouse(false)
    }

    const handleShowGameClick = () => {
        setShowShop(false)
        setShowGame(true)
        setShowHouse(false)
    }

    const [allFoods, setAllFoods] = useState([]);

    const getFoods = () => {
        fetch("http://localhost:8080/foods")
            .then(response => response.json())
            .then(foods => {
                const newFoodList = [];
                for (const food of foods) {
                    newFoodList.push(
                        <Food
                            key={food.foodId}
                            id={food.foodId}
                            name={food.name}
                            price={food.price}
                            energy={food.energy}
                            happiness={food.happiness}
                            heals={food.heals}
                            unhealthy={food.unhealthy}
                            feedPet={feedPet}
                            money={pet.money}
                        />
                    );
                }
                setAllFoods(newFoodList);
            })
            // catch error
            .catch(error => console.error(error))   
    }

    useEffect(getFoods, [pet]);

    return(
        <section>
            {/* conditional render */}
            <div className="pixel-box display break">
           { showHouse ? <House houseNum={pet.house} money={pet.money} upgradeHouse={upgradeHouse} mood={pet.mood}/> : null}
           { showShop ? <>{allFoods}</> : null}
           { showGame  && <MiniGame/> ? <MiniGame wonGame={wonGame}/> : null}
            </div>
            <div className="middle-flex break gap">
                <button onClick={handleShowHouseClick} id="house-btn" className="btn pixel-box selected">House</button>
                <button onClick={handleShowShopClick} id="shop-btn" className="btn pixel-box">Shop</button>
                <button onClick={handleShowGameClick} id="game-btn" className="btn pixel-box">Game</button>
            </div>
        </section>
    );
}

export default ActivityContainer;