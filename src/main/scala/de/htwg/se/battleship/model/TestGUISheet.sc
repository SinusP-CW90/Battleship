import scala.swing._

val frame = new MainFrame {
  title = "My first gui";
  contents = Button("Klick Me!")(println("Button was klicked"));
  size = new Dimension(500,500)
  centerOnScreen()
}
frame.visible = true