#!/usr/bin/env bash

echo "Cleaning up stale appium processes"
ps -ef | grep node | grep [a]ppium | awk {'print $2'} | xargs kill -9 $1