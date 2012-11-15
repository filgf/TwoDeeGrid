# Anleitung zu TwoDeeGrid

## Was ist TwoDeeGrid?

Das `TwoDeeGrid` ist eine graphische Umgebung, welche zum Experimentieren in Java verwendet werden kann. Sie besteht aus einem Raster von quadratischen farbigen Feldern, die im Programmverlauf verändert werden können.

![](pic/Hello.png)

Sie bietet folgende Möglichkeiten:

* Erzeugen von Rastern in beliebiger Größe
* Einfärben der Zellen auf unterschiedliche Art und Weise
* Eingaben über Tasten und Maus
* Warten von Zeitspannen

## Erste Schritte

### Download und Installation

Laden Sie die aktuelle Version [hier herunter](https://raw.github.com/zyxxylabs/TwoDeeGrid/master/dist/TwoDeeGrid.zip) und entpacken Sie das Archiv.

Erstellen Sie ein Java-Projekt in Eclipse und ziehen Sie dann die Datei `twodeegrid.jar` via *Drag-and-Drop* auf den Projektnamen im Package-Explorer auf der linken Seite. Im erscheinenden Dialog wählen Sie die Option *Copy files* und klicken *OK*.

Im Projekt sollte jetzt `twodeegrid.jar` erscheinen. Die Bibliothek muss nun noch für eigene Programme nutzbar gemacht werden. Klicken Sie mit der rechten Maustaste auf `twodeegrid.jar` und wählen Sie den Eintrag *Build Path -> Add to build path*.

Sie können eigene Klassen über das Menü *File -> New -> Class" erstellen. Fügen Sie in die erste Zeile folgenden Import ein:

``` {.java}
import twodeegrid.*;
```

### "Hallo Welt"

Ein einfaches Code-Beispiel: Zunächst wird das Grid so initialisiert, dass es aus 3x3 Zellen besteht und den Fenstertitel "Hello World" trägt. Anschliessend wird der Punkt in der linken oberen Ecke (Koordinate X=0, Y=0) rot eingefärbt: 

``` {.java}
import twodeegrid.*;

public class HelloWorld {
	private static void main(String[] args) {
		TwoDeeGrid g = new TwoDeeGrid(3, 3, "Hello World");
		g.setColorAt(0, 0, CellColor.RED);
	}
}
```

Das Ergebnis bei Ausführen des Programms:

![](pic/HelloWorld.png)

Das Raster bleibt auch bei Beendigung der Main-Methode auf dem Bildschirm. Das Programm ist erst beendet, wenn das Fenster durch Klicken auf das Schließsymbol von Hand geschlossen wird.

### Das Raster
![](pic/Coords.png)

Das Grid besteht aus quadratischen Zellen, die über ihre horizontale (X) und ihre vertikale (Y) Koordinate angesprochen werden können. Die Zählung der Koordinaten beginnt bei 0, die obere linke Ecke ist Koordinate X=0, Y=0.


### Initialisieren des Grid
Das Grid kann durch Erzeugen eine TwoDeeGrid-Objekts erstellt und angezeigt werden. Dazu existieren zwei Konstruktoren:

``` {.java}
public TwoDeeGrid()
public TwoDeeGrid(int xSize, int ySize, String title)
```

Im ersten Fall wird ein Standardraster in Größe 8x8 erstellt. Über den zweiten Konstruktor kann ein beliebig dimensioniertes Grid erstellt und ein Fenstertitel vergeben werden. Die Größe der einzelnen Zellen wird abhängig von der Bildschirmauflösung automatisch angepasst.


## Zellfarben

### Setzen von Zellfarben
``` {.java}
public void setColorAt(int x, int y, CellColor color)
```

Über `setColorAt` kann die Zelle mit Koordinate x und y eingefärbt werden. Der dritte Parameter bezeichnet einen Farbwert, die entweder aus einer Auswahl direkt angegeben werden kann (siehe [Farbwerte](#farbwerte)) oder über verschiedene Funktionen zufällig erzeugt werden kann (siehe [Zufällige Farbwerte](#zufällige-farbwerte)).

Alle Werte werden Modulo der Größe des `TwoDeeGrid` berechnet. Das bedeutet, dass Wenn für die Koordinate x ein Wert größer oder gleich der Breite des Rasters angegeben wird, die Nummerierung wieder am linken Rand des `TwoDeeGrid` beginnt. Wenn ein Wert kleiner als 0 gewählt ist, ist eine Zelle ausgehend vom rechten Rand des Rasters gemeint.

Folgendes Codebeispiel...

``` {.java}
TwoDeeGrid g = new TwoDeeGrid(4,4, "TwoDeeGrid");
g.setColorAt(1, 3, CellColor.YELLOW);
g.setColorAt(-1, 0, CellColor.RED);
g.setColorAt(4, 1, CellColor.GREEN);
g.setColorAt(2, -1, CellColor.BLUE);
g.setColorAt(103, 2, CellColor.BLACK);
```

...ergibt:
		
![](pic/Modulo.png)

``` {.java}
public void clear()
```

löscht das Raster und setzt es auf seinen weissen Ursprungszustand zurück.

### Auslesen von Zellfarben

``` {.java}
CellColor getColorAt(int x, int y)
```

gibt den Farbwert an der Position x,y zurück. Der Farbwert kann mit den definierten [Konstanten](#farbwerte) verglichen werden. 

Beispiel:

``` {.java}
if (g.getColorAt(1, 3) == CellColor.YELLOW) {
  // Your Program here...
}
```

### Farbwerte

Alle Farbwerte stehen über in der Klasse `CellColor` definierte Konstanten zur Verfügung:

![](pic/Colors.png)

Eine Sonderrolle nimmt die weisse Zellfarbe ein. Sie bezeichnet ein ungefülltes Feld im Raster und wird deshalb mit der Konstanten `CellColor.NONE` bezeichnet.

Beispiel:

``` {.java}
TwoDeeGrid g = new TwoDeeGrid(3, 3, "Hello World");
g.setColorAt(1, 1, CellColor.GREEN_LIGHT);
```

### Zufällige Farbwerte

Zellfarben können auch zufällig erzeugt werden: 

``` {.java}
public static CellColor getRandomCellColor() 
```

...gibt ein `CellColor`-Objekt von zufälliger Farbe zurück.

``` {.java}
public static CellColor getRandomRedBlue() 
```

...gibt zufällig `CellColor.RED` oder `CellColor.BLUE` zurück.

``` {.java}
public static CellColor getRandomOnOff() 
```

...gibt zufällig `CellColor.NONE` oder `CellColor.BLACK` zurück.

``` {.java}
public static CellColor getRandomOnOff(double probabilityOn) 
```

...gibt zufällig `CellColor.NONE` oder `CellColor.BLACK` zurück. Über den Parameter `probabilityOn` kann angegeben werden, mit welcher Wahrscheinlichkeit ein Feld "On" (d.h. schwarz) sein soll. 0.0 entspricht 0% Wahrscheinlichkeit, 1.0 entspricht 100% Wahrscheinlichkeit, 0.5 entspricht 50% Wahrscheinlichkeit,...


## Bearbeiten des Grid als Array

``` {.java}
public CellColor[][] getCellArray()
```

liefert eine Kopie des gesamten `TwoDeeGrid` in Form eines zweidimensionalen Array aus Farbwerten (`CellColor`). Diese Array kann frei verändert werden. Änderungen werden aber nicht sofort sichtbar, sondern können über die Methode

``` {.java}
public void updateCellArray(CellColor[][] cells)
```

wieder an das Grid übergeben und angezeigt werden.

Das Arbeiten mit einem Array statt mit `getColorAt()` und `setColorAt()` lohnt sich vor allem, wenn eine große Menge - oder alle - Zellen auf einmal verändert werden sollen.

Folgendes Codebeispiel...

``` {.java}
TwoDeeGrid g = new TwoDeeGrid(5, 5, "Hello Array!");

CellColor[][] cells = g.getCellArray();

for (int x = 0; x < cells.length; x++) {
  for (int y = 0; y < cells[x].length; y++) {
    cells[x][y] = CellColor.getRandomRedBlue();
  }
}
		
g.updateCellArray(cells);
```

...ergibt:

![](pic/HelloArray.png)


## Zeitmessung

``` {.java}
public void waitTime(double time)
```

hält die Programmausführung für eine bestimmte Zeitspanne an. Der Parameter `time` legt die Wartezeit in Sekunden fest. 

`waitTime` kann als Basis für Animationen dienen. Folgendes Beispiel erzeugt ein Grid mit einem blinkenden roten Quadrat in der Mitte:


``` {.java}
TwoDeeGrid g = new TwoDeeGrid(3, 3, "Blink!");

while(true) {
  g.setColorAt(1, 1, CellColor.RED);
  g.waitTime(0.3);
  g.setColorAt(1, 1, CellColor.NONE);
  g.waitTime(0.6);
}
```

## Benutzereingabe

Über Benutzereingaben kann ein Programm interaktiv gestaltet werden und auf Eingaben reagieren. Das `TwoDeeGrid` bietet dazu zwei Möglichkeiten: Maus (oder Touchscreen) und Tastatur.

### Maus 

``` {.java}
public MouseClick waitMouseClick()
```

wartet bis der Nutzer auf eine Zelle des Rasters geklickt hat. Die Methode gibt dann ein Objekt vom Typ `MouseClick` zurück.

`MouseClick` speichert Informationen über den Klick und bietet diese über folgende Methoden an:

``` {.java}
public int getX()
public int getY()
public int getType()
```

`getX()` und `getY()` geben die X- bzw. die Y-Koordinate der angeklickten Zelle an. `getType()` gibt an, welche Maustaste gedrückt wurde: die linke (`MouseClick.LEFT`), die rechte (`MouseClick.RIGHT`) oder die mittlere (`MouseClick.MIDDLE`) Maustaste.

Dieses Beispiel erlaubt es auf dem Grid mit der Maus zu zeichnen:
``` {.java}
public static void main(String[] args) {
  TwoDeeGrid g = new TwoDeeGrid(5,5, "TwoDeeGrid");
  
  while(true) {
    MouseClick click = g.waitMouseClick();
    g.setColorAt(click.getX(), click.getY(), CellColor.getRandomCellColor());
  }
}
```

### Tastatur

``` {.java}
public int getKeyPressed()
```

gibt die zuletzt auf der Tastatur gedrückte Taste zurück. Im Gegensatz zu `waitMouseClick()` wartet `getKeyPressed()` nicht bis eine Taste gedrückt wurde.

Die Tastenereignisse sind in der Klasse [`KeyEvent`](http://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyEvent.html) des JDK kodiert. Falls keine Taste gedrückt wurde, wird `KeyEvent.CHAR_UNDEFINED` zurückgegeben.

Folgendes Beispiel öffnet ein schmales Fenster. Das grüne Quadrat kann mit den Pfeiltasten nach links und rechts bewegt werden:

``` {.java}
TwoDeeGrid g = new TwoDeeGrid(9, 1, "Press LEFT/RIGHT");
int pos = 4; 
int key;

while (true) {
  g.clear();
  g.setColorAt(pos, 0, CellColor.GREEN);
  
  do {
    key = g.getKeyPressed();
  } while (key == KeyEvent.CHAR_UNDEFINED);
  
  switch (key) {
  case KeyEvent.VK_LEFT:
    pos--;
    break;
  case KeyEvent.VK_RIGHT:
    pos++;
    break;
  }
}
```


## Referenz (Klassen und Methoden)

### TwoDeeGrid

* `public TwoDeeGrid(int xSize, int ySize, String title)` \
  Erzeugt ein Grid mit der Größe `xSize` x `ySize` und dem Titel `title`.

* `public TwoDeeGrid()` \
  Erzeugt ein Grid der Größe 8x8.

* `public int getGridSizeX()`\
  Liefert die Breite des Rasters (Anzahl der Zellen) zurück.

* `public int getGridSizeY()`\
  Liefert die Höhe des Rasters (Anzahl der Zellen) zurück.

* `public CellColor getColorAt(int x, int y)`\
  Liefert den Farbwert an Position (`x`,`y`). (0, 0) entspricht der linken oberen Ecke.

* `public void setColorAt(int x, int y, CellColor color)`\
  Setzt den Farbwert an Position (`x`,`y`) auf `color`. (0, 0) entspricht der linken oberen Ecke.

* `public void clear()`\
  Löscht das Grid und färbt alle Zellen weiß.

* `public CellColor[][] getCellArray()`\
  Liefert das Raster als zweidimensionales Array. Auf die Farbwerte kann direkt über `cell[x][y]` zugegriffen werden.

* `public void updateCellArray(CellColor[][] cells)`\
  Setzt die Zellen des Grid auf die in `cells` gespeicherten Farbwerte.

* `public void waitTime(double time)`\
  Wartet `time` Sekunden.

* `public MouseClick waitMouseClick()`\
  Wartet, bis eine der Zellen angeklickt wurde. Liefert ein Objekt mit zusätzlichen Informationen zurück.

* `public int getKeyPressed()`\
  Gibt den Code der zuletzt gedrückten Taste zurück, entsprechend der Codes in [`KeyEvent`](http://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyEvent.html).


### CellColor

* `public static CellColor getRandomCellColor()`\
  Liefert einen zufälligen Farbwert.

* `public static CellColor getRandomRedBlue()`\
  Liefert zufällig und gleichwahrscheinlich `CellColor.RED` oder `CellColor.BLUE`.

* `public static CellColor getRandomOnOff()`\
  Liefert zufällig und gleichwahrscheinlich `CellColor.BLACK` oder `CellColor.NONE`.

* `public static CellColor getRandomOnOff(double probabilityOn)`\
  Liefert zufällig `CellColor.BLACK` (mit Wahrscheinlichkeit `probabilityOn`) oder `CellColor.NONE`(mit Wahrscheinlichkeit `1 - probabilityOn`).

Definierte Farbkonstanten: siehe [Farbwerte](#farbwerte).

### MouseClick

* `public int getX()`\
  X-Koordinate der angeklickten Zelle.

* `public int getY()`\
  Y-Koordinate der angeklickten Zelle.

* `public int getType()`\
  Gedrückte Maustaste (`MouseClick.LEFT`, `MouseClick.RIGHT` oder `MouseClick.MIDDLE`).