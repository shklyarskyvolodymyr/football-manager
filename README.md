# Football-manager

It's a game where you can be manager of a team. Each team has it's own players with skills, 
these skills can be trained after each match regarding how strong is your trainer. 
you can buy or sell these players and also earn money after each match 
from sold tickets or from sponsors.
Your team will participate in league with 7 other teams, there are several league ranks.
During one season you will play against all teams from your league two matches, one home
and one away.

## Match engine

Each player have different skills: defense, playmaking, attacking and goalkeeping.
Your teams power will be calculated based on skills of your 11 players.
For example you have three attackers one of them have 7 defending rank and rest
two have 6 rank and as a result your defending will be 19 (7+6+6=19).

Every match have 10 situation, it will be divided to each teams based
on playmaking power. For example you have 20 playmaking and your opponent has 15,
then you will have 57% change of starting attack from one situation (20/(20+15)).
If you score a goal or not that will be calculated on same principle from your attacking
power and your opponents defence power.

Goalkeepers add their goalkeeping skill into defence. 

Winger players add their playmaking skill into playmaking.

### Technologies
- Java 17
- Gradle
- Docker
- Spring Boot
- PostgreSQL
- Kafka
- Redis
- Java concurrency -> reactive programing
