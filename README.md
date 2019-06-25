"# JavaBasics" 
## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [Status](#status)

## General info
Basics Java , still in progress adding new functionality

## Technologies
* Java - version 12
* gson - version 2.8.4
* maven - version 3.6

## Setup
download, compile and run

## Code Examples
Map<String, Car> map = cars.stream()
                .collect(Collectors.groupingBy(c -> c.getModel()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        m -> m.getKey(),
                        m -> m.getValue().stream()
                                .sorted(Comparator.comparing(s -> s.getPrice(), Comparator.reverseOrder()))
                                .findFirst()
                                .orElseThrow(() -> new IllegalStateException(" CAN'T found ")
                                )));

## Features

To-do list:
- cleanCode - optimize code 
- Junit
- Json



## Status
Project is: _in_progress_

