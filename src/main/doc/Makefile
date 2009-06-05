all: overview.png diagram.png
	open overview.png diagram.png

overview.png: overview.dot
	dot -T png -o overview.png overview.dot

diagram.png: diagram.dot
	dot -T png -o diagram.png diagram.dot
