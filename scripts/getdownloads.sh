#!/bin/bash

while  read link 
do
	echo $link
	wget  -qO-  gen.lib.rus.ec/$link  --random-wait
done
