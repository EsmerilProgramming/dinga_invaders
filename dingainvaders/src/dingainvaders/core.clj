(ns dingainvaders.core)

(import javax.swing.JFrame)
(import java.awt.event.KeyListener)
(import java.awt.image.BufferStrategy)
(import java.awt.image.BufferedImage)
(import java.awt.TexturePaint)
(import java.awt.Rectangle)
(import java.awt.Graphics2D)

;[4] i guess this is a better approach:
; http://clojure.org/jvm_hosted

; [1] we need this:
; private BufferStrategy bufferStrategy;
; private BufferedImage bgImage;


;(defn paint-bg) ; [3] we need need this:
                ; Graphics2D g = (Graphics2D)bufferStrategy.getDrawGraphics();
                ; bgImage = load an image using clojure here..
                ; g.setPaint(new TexturePaint(bgImage, new Rectangle(0, 1, bgImage.getWidth(), bgImage.getHeight())));
                ; g.fillRect(0, 0, getWidth(), getHeight()); not sure if we need this, btw width and height are from frame...

(defn -main
  [& args]
  (doto (JFrame. "Dinga Invaders")
    (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
    (.setSize 800 600)
    (.setLocationRelativeTo nil)
    (.setExtendedState JFrame/MAXIMIZED_BOTH)
    (.setUndecorated true)
    (.setVisible true)
    (.createBufferStrategy 2)
    ; [2] then set the bufferStrategy with:
    ; bufferStrategy = getBufferStrategy();
    (.requestFocus)))
