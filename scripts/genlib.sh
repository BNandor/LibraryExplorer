#!/bin/bash

if [ "$#" -ne 1 ] 
then
#	echo '[]'
	exit 1;
fi
sanitizedinput=`echo $1 | sed 's/ /+/g'`
page=`wget -qO-   "http://gen.lib.rus.ec/search.php?req=$sanitizedinput&open=0&res=25&view=simple&phrase=1&column=def"`
#result=`echo $page | ./getget.sh | ./getbook.sh`
resultbooklinks=`echo $page | grep -o "href='[^\ ][^\ ]*'  *title='' id" | grep -o 'href='[^\ ][^\ ]*' ' | grep -o '=..*' | sed 's/^=//g' | tr "'" " " | sed 's/ //g'`
#echo '['
for booklink in $resultbooklinks 
do
	 getlink=`wget  -qO-  gen.lib.rus.ec/$booklink  --random-wait| grep -o 'a href="..*title..*Gen\.lib\.rus' | grep -o 'href="[^ ]*"' | cut -d '='  -f2 | sed 's/^"\(..*\)/\1/g' | sed 's/\(..*\)"$/\1/g' `
	
#		echo "{"
			html=`wget -qO- $getlink`
			domain=`echo "$getlink" | cut -d '/' -f1,2,3`

			dlink=`echo $html | grep -o "href="..*"..*GET" | sed 's/href="\(..*\)"..*/\1/g' `
			#echo '"download-link":''"'$dlink'"'
			echo $dlink
			#echo ","
			img=`echo $html | grep -o 'img  *src="..*"  *alt="cover"' | sed 's/..*src="\([^ ][^ ]*\)"..*/\1/g'`
			#echo '"cover-link":''"'$domain/$img'"'
			echo $domain/$img
			#echo ","
			title=`echo $html | grep '<h1>' | grep -o "<h1>[^><]*<" | sed 's/<h1>//g' | sed 's/\(..*\)<$/\1/g'`
			#echo '"title":''"'$title'"'
			echo $title
			#echo ","
			author=`echo $html | grep  'Author' |grep -o '>[ ]*Author[^<>][^<>]*<' | sed 's/\(..*\)<$/\1/g' | sed 's/^>\(..*\)/\1/g'`
			#echo '"author":''"'$author'"'
			echo $author

#		echo "}"
#		echo ","
done
#echo '"dummy"'
#echo "]"
