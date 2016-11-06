
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Ball{
	
	Circle ball = new Circle();
	
	boolean ended = false, start = false;
	double[] info;
	Text txtPoints;
	BorderPane border;
	
	int points = 0;
	int dir;
	int radius = 20;
	double ballSpeed = 2; 
	
	public Ball(double[] info, Text txtPoints, BorderPane border){
		this.info = info;
		this.txtPoints = txtPoints;
		this.border = border;
		
		dir = ((int)(Math.random() * 4));
		
		ball.setRadius(radius);
		ball.setCenterX((int)((Math.random() * (info[0] - (radius*2))) + radius));
		ball.setCenterY((int)((Math.random() * (info[1] - (radius*2))) + radius));
	}
	
	public void frame(){
		
		if (start){
			dir = 0;
			start = false;
		}
		
		if (dir == 0){
			if (ball.getCenterX() - radius >= info[0]){
				dir = 3;
			}
			if (ball.getCenterY() - radius >= info[1]){
				dir = 1;
			}
			ball.setCenterX(ball.getCenterX() + ballSpeed);
			ball.setCenterY(ball.getCenterY() + ballSpeed);
		}
		if (dir == 1){
			if (ball.getCenterX() - radius >= info[0]){
				dir = 2;
			}
			if (ball.getCenterY() <= radius){
				dir = 0;
			}
			ball.setCenterX(ball.getCenterX() + ballSpeed);
			ball.setCenterY(ball.getCenterY() - ballSpeed);
		}
		if (dir == 2){
			if (ball.getCenterX() <= radius){
				if (info[7] == 0){
					dir = 1;	
				}else{
					ended = true;
				}
			}
			if (ball.getCenterY() <= radius){
				dir = 3;
			}
			if (ball.getCenterY() + radius >= info[6] && ball.getCenterY() - radius <= info[6] + info[3]
					&& ball.getCenterX() - radius <= info[5] + info[2] && ball.getCenterX() - radius >= info[5]
                    && info[7] != 0){
				dir = 1;
				ballSpeed += ballSpeed < 10 ? 1 : 0;
				info[4] += ballSpeed < 10 ? 1 : 0;
				points++;
				txtPoints.setText("" + points);
				if (points == 10){
					border.setLayoutX(150);
				}
				System.out.println("Points: " + points + "\nBallSpeed: " + ballSpeed);
			}
			ball.setCenterX(ball.getCenterX() - ballSpeed);
			ball.setCenterY(ball.getCenterY() - ballSpeed);
		}
		if (dir == 3){
			if (ball.getCenterX() <= radius){
				if (info[7] == 0){
					dir = 0;	
				}else{
					ended = true;
				}
			}
			if (ball.getCenterY() - radius >= info[1]){
				dir = 2;
			}
			if (ball.getCenterY() + radius >= info[6] && ball.getCenterY() - radius <= info[6] + info[3]
					&& ball.getCenterX() - radius <= info[5] + info[2] && ball.getCenterX() - radius >= info[5]
                    && info[7] != 0){
				dir = 0;
				ballSpeed += ballSpeed < 10 ? 1 : 0;
				info[4] += ballSpeed < 10 ? 1 : 0;
				points++;
				txtPoints.setText("" + points);
				if (points == 10){
					border.setLayoutX(150);
				}
				System.out.println("Points: " + points + "\nBallSpeed: " + ballSpeed);
			}
			ball.setCenterX(ball.getCenterX() - ballSpeed);
			ball.setCenterY(ball.getCenterY() + ballSpeed);
		}
		
		
	}
	
	public Circle returnNode(){
		return ball;
	}
	
	public boolean ended(){
		return ended;
	}
}
