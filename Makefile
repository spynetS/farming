.PHONY: run build

run:
		_JAVA_AWT_WM_NONREPARENTING=1 mvn compile exec:java

build:
		cd ~/dev/speler && mvn install
