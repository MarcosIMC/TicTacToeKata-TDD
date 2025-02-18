public class TicTacToe {
	Grid grid;
	Player[] players;

	public void start() {
		createPlayers();
		createGrid();
		whoPlayerStart();
	}

	private void createPlayers() {
		Player player1 = new Player(randomPiece());
		Player player2 = new Player(player1.piece == GamePieces.X ? GamePieces.O : GamePieces.X);
		this.players = new Player[] {player1, player2};
	}

	private void createGrid() {
		this.grid = new Grid();
	}

	private GamePieces randomPiece() {
		int initialRandomPiece = (int) (Math.random() * 1);
		
		return initialRandomPiece == 0 ? GamePieces.X : GamePieces.O;
	}

	public void setAPiece(Player player, int coordinateX, int coordinateY) {
		this.grid.setAPiece(player.piece, coordinateX, coordinateY);
	}

	public boolean haveAPieceIn(int coordinateX, int coordinateY) {
		return this.grid.haveAPieceIn(coordinateX, coordinateY);
	}

	public Grid getGrid() {
		return this.grid;
	}

	public Player[] getPlayers() {
		return this.players;
	}

	public boolean isFinished() {
		return !this.grid.haveFreeSpace();
	}

	private void whoPlayerStart() {
		players[0].setTurn(getRandomTurn());

		if (players[0].getTurn() == YourTurn.NO) {
			players[1].setTurn(YourTurn.YES);
		}
	}

	private static YourTurn getRandomTurn() {
		YourTurn[] values = YourTurn.values();
		int randomIndex = (int) (Math.random() * values.length);
		return values[randomIndex];
	}

	public boolean anyPlayerWinInARow() {
		return grid.anyRowHaveTheSamePiece();
	}

	public boolean anyPlayerWinInAColumn() {
		return grid.anyColumnHaveTheSamePiece();
	}

	public boolean anyPlayerWinInADiagonal() {
		return grid.anyDiagonalHaveTheSamePiece();
	}
}
