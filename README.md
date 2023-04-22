# Distributed Movie Ticket Booking System (DMTBS)

## Table of Contents
- [Authors](#authors)
- [Abstract](#abstract)
- [Implementation](#implementation)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [Contributing](#contributing)

## Authors
- Aryan Saxena
- Rohan Dhiman

## Abstract
Distributed Movie Ticket Booking System or DMTBS is implemented over the Web Services to be highly available under a single process crash failure or to tolerate a single software failure. In an event of the crash failure, the server implementation has the essential code to withstand a software failure and will always remain highly available.

This project was completed as part of a Master's degree in Computer Science at Concordia University and was one of the best projects in its class.

## Implementation
There are three replicas of the actively replicated DMTBS server system, each of which uses a distinct implementation in different hosts. Every replica has a Replica Manager (RM), which identifies the problem and fixes the errors accordingly. Additionally, front end (FE) has been implemented using Java Swing, which accommodates the clients and sends it to the sequencer.

In case of any software failure or fault that might occur, the three replicas take actions to perform necessary process of the client’s request and reverts the results to the front end, which will always return the correct and accurate result to the client.

The front end also notifies the RMs in case of an inaccurate result from the replicas. The RMs are responsible for replacing a replica with an accurate one if there are three failed client requests consecutively from the same replica. The front end will assume that the error may have arisen due to crashing of a replica and reverts to the RMs in case the result is not received within a certain duration of time, which may be set as twice the time required by the slowest replica. If the replica managers finalize that the replica has crashed, they inspect and find out the fault and then replace it with another.

All server-server communication, and communication between server replicas, sequencer, FE, and RM communicate with one another via UDP protocol only.

## Technologies Used
- Backend language: Java
- Frontend: Java Swing

## Getting Started
To get started with DMTBS, follow these steps:
1. Clone this repository.
2. Install dependencies.
3. Run all the servers on different machines.
4. Run the sequencer (If all the servers worked perfectly then only it will work)
5. Finally, run `java login.java` to finally run the project.
6. Have fun!

## Contributing
Contributions are welcome!