(ns dinga_invaders.data
(:import java.awt.image BufferedImage))

; tempo antes de uma interacao do loop do jogo para calular FPS
(def start-cycle-process 0)
; tempo apos um ciclo terminar para calcular FPS
(def end-cycle-process 1000)
; incrementa coordenada Y de um retangulo usado para textura
; de background, ocorre uma cópia da img de background para TexturePaint
; para fazer efeito vertical scrolling é necessário incrementar o eixo Y
; do retangulo usado para construir esse o objeto textura ( no java )
(def texture-background-y 0)

; representa o espaço em background
(let [space (BufferedImage.)])


