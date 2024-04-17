// package com.miniGamesArcade.brickBreaker;



// import java.awt.event.KeyListener;

// import java.awt.event.KeyEvent;

// // seperating inputs ki file so it doesnt get messy

// // we extend a class but implment a interface 
// public class KeyboardInputs implements KeyListener {
//     private Panel panel;
    
//     public KeyboardInputs(Panel panel){
//         this.panel=panel;
//         //intializing the gamepanel , that the upar defined gamepanel is the same one that we are getting in the constructor
//     }
//     @Override
//     public void keyTyped(KeyEvent e) {
//         System.out.println("key types");
        
//     }

//     @Override
//     public void keyPressed(KeyEvent e) {
        
//         switch(e.getKeyCode()){
            
//             case KeyEvent.VK_A:
//                 panel.changeX(-5);
//                 System.out.println("A");
//                 break;
//             case KeyEvent.VK_D:
//                 panel.changeX(+5);
//                 break;
//         }
//     }

//     @Override
//     public void keyReleased(KeyEvent e) {
//         System.out.println("key released ");
//     }
// }
