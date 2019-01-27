# Software Engineering - Progetto 6
## Requirements Analysis

Software di integrazione all'analisi dei requisiti tramite grafo.
Il software è formato da tre packages. Req2Graph che può essere utilizzato come libreria, ViewReq2Graph interfaccia grafica sviluppata in JavaFx e testReq2Graph che contiene le classi di test.

## Informazioni
Queste istruzioni permettono di avere una copia del software e di eseguirlo ai fini di sviluppo o testing.

### Prerequisiti
Lo siluppo è stato eseguito su una macchina virtuale con installata una distribuzione Ubuntu con i seguenti softare installati:
Eclipse
Java 8
Openjfx (libreria javafx)
GraphViz (gestione grafica dei grafi)

### Installazione Java
Verificare di avere Java installato:
```
$ java -version
java version "1.8.0_171"
Java(TM) SE Runtime Environment (build 1.8.0_171-b11)
Java HotSpot(TM) 64-Bit Server VM (build 25.171-b11, mixed mode)
```
Nel caso non si avesse Java ancora installato è possibile installare Oracle Java o OpenJDK.
Per il progetto è stata utilizzata la versione java8 di Oracle. E' possibile scaricarla dal repository PPA di Webupd8 Team:
```
$ sudo add-apt-repository ppa:webupd8team/java
$ sudo apt-get update
$ sudo apt-get install oracle-java8-installer
```
### Installazione librerie JavaFx
```
$ sudo apt-get install openjfx
```
### Installazione programma GraphViz
```
$ sudo apt-get install graphviz
```

```
###  installazione Eclipse IDE

Nel caso in cui non si avesse Eclipse installato è possibile seguire i seguenti passi per l'installazione:
Scaricare la versione desiderata di Eclipse IDE dal sito di [Eclipse](https://www.eclipse.org/downloads)
Una volta scaricato il pacchetto usiamo il comando `tar` per estrarlo:
```
$ tar xzf eclipse-inst-linux64.tar.gz
Siamo pronti ad installare Eclipse IDE. L'eseguibile per l'installazione è situato nella cartella appena estratta `eclipse-installer`.
Iniziare ad installare Eclipse IDE eseguendo il seguente comando:
```
$ ./eclipse-installer/eclipse-inst
```
Il primo passo consiste nell'aggiornare l'installer di Eclipse IDE. 
Cliccare sull'icona del menù in alto a sinistra e cliccare su `UPDATE`.
Selezionare `Remember accepted licenses` e cliccare `Accept`.
Cliccare su `OK` per riavviare l'installer.
Installare Eclipse IDE selezionando `Eclipse IDE for Java Developers`.

### Settaggio Eclipse IDE e aggiunta plugin

A questo punto è possibile aprire Eclipse IDE e prepararsi ad aggiungere i componenti aggiuntivi per il funzionamento del software.
L'applicazione Req2Graph utilizza la libreria JavaFx quindi è necessario installare il componente aggiuntivo.
Dal menù di Eclipse andare su `Help-> Eclipse Marketplace...` e cercare `E(fx)clipse`. 
Quindi premere su `install` per installare il componente aggiuntivo.

L'applicazione Req2Graph crea due grafi multigraph.dot e pseudograph.dot. Per visulaizzarli in formato grafico è necessario installare il plugin eclipsegraphviz.
Dal menù di Eclipse andare su `Help-> Eclipse Marketplace...` e cercare `TextUml`. 
Quindi premere su `install` per installare il componente aggiuntivo.
Per visualizzare il grafo andare su `Window-> Show View-> Other...` ed infine su `EclipseGraphViz-> Image Viewer` e tramite Drag&Drop trascinare il file.dot.

Al termine dell'installazione riavviare Eclipse.

## Importazione del Software

Per caricare il progetto su Eclipse IDE è necessario clonare il progetto da GitHub, quindi estrarre il file `Req2Graph-master.zip`.
Su Eclipse IDE andare su `File -> Import -> Existing Project into Workspace`, selezionare la directory `Req2Graph-master` appena estratta, quindi selezionare `copy projects into workspace` e cliccare su `finish`.

## Eseguire il Software
Per eseguire il softare tasto destro sull'icona del progetto, `Run As -> Java Application`, si apirà una finestra con selezionata la voce `Main-it.unige.viewreq2graph` quindi cliccare su `OK`.





