public class Player {
	GamePieces piece;
	YourTurn turn;
	public Player(GamePieces piece) {
		this.piece = piece;
		this.turn = YourTurn.NO;
	}

	public GamePieces getPiece() {
		return piece;
	}

	public void setPiece(GamePieces piece) {
		this.piece = piece;
	}

	public YourTurn getTurn() {
		return turn;
	}

	public void setTurn(YourTurn turn) {
		this.turn = turn;
	}
}
