read page
#echo $page
echo $page |./getinitiallinks.sh | ./getdownloads.sh  | grep -o 'a href="..*title..*Gen\.lib\.rus' | grep -o 'href="[^ ]*"' | cut -d '='  -f2 | sed 's/^"\(..*\)/\1/g' | sed 's/\(..*\)"$/\1/g'
