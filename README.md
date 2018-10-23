# BookingGo Technical Assessment

## Minimum Requirements
Java 1.8

Maven 3.5.1

Eclipse IDE

## Build

Clone repository from
```
https://github.com/davidiozu/BookingGo.git
```
Import it into *Eclipse* as an existing *Maven* project.


## Run
### Part 1
Both sub-exercises from Part 1 can be run as

**Right click** on either *Part1A.java* or *Part1B.java* -> **Run As** -> **Run Configurations** -> **Arguments** .

Insert the arguments list as:
```
{pick up latitude},{pick up longitude} {drop off latitude},{drop off longitude} {number of passengers}
```

Replace the {content} with your input.

*Note* : for both parts the **{number of passengers}** is optional and can the program can run either with or without it.

### Part 2
**Right click** on *Part2.java* -> **Run As** -> Java Application

The server can be queried as:
```
http://localhost:10000/DIbookinggo?pickup={pick up latitude},{pick up longitude}&dropoff={drop off latitude},{drop off longitude}&passengers={number of passengers}
```

Replace the {content} with your input.

*Note* : **&passengers={number of passengers}** is again optional and can the program can run either with or without it.

Example
```
http://localhost:10000/DIbookinggo?pickup=1,1&dropoff=2,2&passengers=2
```


