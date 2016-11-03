# Vehicles

This is a graphic implementation of a Linked List (done as coursework for Computer Science II). The files given to us were TrainEngine.java and RailCar.java. The vehicle superclass and all other files are my own.

How to use it:
When the program launches, the first 7 clicks on the background will generate a train engine, 5 rail cars, and a stack (queue, if you want to be technical about it) of boxes. All of the vehicles can be clicked and moved. The box stack can not.

Adding trailers:

Trailers can be added to a vehicle in two ways. The first is by dragging and dropping a trailer on another trailer or the train engine. If their respective hitboxes connect, the trailers join each other. The other way is to select a trailer and use the List menu in the top of the screen to add it to the engine at one of the positions given. 

Removing trailers: 

Trailers can only be removed from the engine itself. Use the List menu and any of the remove options to remove a trailer. 

Adding/Removing a load to/from the trailers: 

Each trailer can carry one box from the queue. The stack of boxes is implemented as a queue (which makes no sense because individual boxes are removed from the bottom). With a rail car selected, click on Remove (in the Queue menu) to remove a box from the queue and place it on the trailer. If the engine is selected, the boxes will be places on its trailers. Removing a box from a rail car works the same way, but you use the "Add" option in the Queue menu.
