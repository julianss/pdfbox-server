# pdfbox-server


To compile:
```
javac -cp pdfbox-app-2.0.1.jar:. pdfboxserver/*.java
```

To start the server:
```
java -cp pdfbox-app-2.0.1.jar:. pdfboxserver.PDFBoxServer
```

The server will listen on port 9998.

To parse a file, send to the server the absolute path of the file to parse followed by a null character.

This is meant to work on the same host (as you don't actually send the file). It's simply to avoid starting PDFBox each time a new file must be parsed.
