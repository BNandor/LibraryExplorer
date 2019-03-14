read page
echo $page | grep -o "href='[^\ ][^\ ]*'  *title='' id" | grep -o 'href='[^\ ][^\ ]*' ' | grep -o '=..*' | sed 's/^=//g' | tr "'" " " | sed 's/ //g'
