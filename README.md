Battle Simulation - Spring Boot App

This is a small Spring Boot project where you simulate a medieval battle.  
You have 5 platoons and your opponent has 5 platoons.  
The goal is to arrange your platoons smartly so that you win at least 3 out of 5 battles.

-------------------------------------------------------------------------------------------------
How it works

- Each platoon has a "type" (like Militia, Spearmen, Foot Archer, etc.) and a "number of soldiers".
- Some platoon types have an advantage over others (example: Militia beats Spearmen).
- If your platoon has an advantage, one soldier counts as two.
- The API will calculate and suggest a winning arrangement for your troops — if possible.

----------------------------------------------------------------------------------------------------

Tech used

- Java 24
- Spring Boot
- Spring Data JPA
- MySQL
- Maven

-------------------------------------------------------------------------

How to run

1. Make sure you have MySQL running locally.
2. Create a database called:
   battle_db


3. Update your `src/main/resources/application.properties` with your MySQL username and password.

4. Open the project in your IDE (IntelliJ).
5. Run the `BattleApplication` file.

Or, if you prefer terminal:
mvn clean install
mvn spring-boot:run


------------------------------------------------------------------------

How to use

After starting the server, send a `POST` request to:

http://localhost:8080/api/battle/simulate


With a JSON body like this:


{
"ownPlatoons": [
{"unitClass": "Spearmen", "soldierCount": 10},
{"unitClass": "Militia", "soldierCount": 30},
{"unitClass": "FootArcher", "soldierCount": 20},
{"unitClass": "LightCavalry", "soldierCount": 1000},
{"unitClass": "HeavyCavalry", "soldierCount": 120}
],
"opponentPlatoons": [
{"unitClass": "Militia", "soldierCount": 10},
{"unitClass": "Spearmen", "soldierCount": 10},
{"unitClass": "FootArcher", "soldierCount": 1000},
{"unitClass": "LightCavalry", "soldierCount": 120},
{"unitClass": "CavalryArcher", "soldierCount": 100}
]
}

You can use Postman or any API client.

- If a winning arrangement is possible, it will return it.
- If not, it will say: `There is no chance of winning`.

-----------------------------------------------------------------------------------------

Quick note

- This project uses JPA to save platoon info.
- Right now it focuses on simulating battles.
- You can extend it later with user login, battle history, etc. if you want.

