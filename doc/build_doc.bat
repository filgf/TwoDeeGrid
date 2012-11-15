pandoc -markdown --ascii --highlight-style pygments -c http://fonts.googleapis.com/css?family=PT+Sans -c pandoc.css -o ReadMe_DE.html ReadMe_DE.md
pandoc -markdown --ascii --highlight-style pygments -c http://fonts.googleapis.com/css?family=PT+Sans -c pandoc.css -o ReadMe_EN.html ReadMe_EN.md
pandoc -markdown --ascii --highlight-style pygments -c http://fonts.googleapis.com/css?family=PT+Sans -c pandoc.css -o index.html index.md
pause