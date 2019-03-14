#!/bin/bash
echo "["
while  read link 
do
echo "{"
	html=`wget -qO- $link`
	domain=`echo "$link" | cut -d '/' -f1,2,3`

	dlink=`echo $html | grep -o "href="..*"..*GET" | sed 's/href="\(..*\)"..*/\1/g' `
	echo '"download-link":''"'$dlink'"'
	echo ","
	img=`echo $html | grep -o 'img  *src="..*"  *alt="cover"' | sed 's/..*src="\([^ ][^ ]*\)"..*/\1/g'`
	echo '"cover-link":''"'$domain/$img'"'
	echo ","
	title=`echo $html | grep '<h1>' | grep -o "<h1>[^><]*<" | sed 's/<h1>//g' | sed 's/\(..*\)<$/\1/g'`
	echo '"title":''"'$title'"'
	echo ","
	author=`echo $html | grep  'Author' |grep -o '>[ ]*Author[^<>][^<>]*<' | sed 's/\(..*\)<$/\1/g' | sed 's/^>\(..*\)/\1/g'`
	echo '"author":''"'$author'"'

echo "}"
echo ","
done
echo '"'dummy'"'
echo "]"
