#!/bin/bash
clear

echo "CCchallenge Solution by Srijan Magapu"


javac src/TweetWordCount/TweetWordCount.java
java -cp src TweetWordCount.TweetWordCount /src/TweetInput/Tweet.txt /src/TweetOutput/ft1.txt

javac src/TweetWordCount/TweetMedianCount.java
java -cp src TweetWordCount.TweetMedianCount /src/TweetInput/Tweet.txt /src/TweetOutput/ft2.txt