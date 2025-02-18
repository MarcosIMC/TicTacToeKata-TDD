import java.util.Arrays;

public class Grid {
	private final int width;
	private final int height;
	private final GamePieces[][] grid;

	public Grid() {
		this.width = 3;
		this.height = 3;
		this.grid = new GamePieces[this.width][this.height];
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void setAPiece(GamePieces piece, int coordinateX, int coordinateY) {
		this.grid[coordinateX][coordinateY] = piece;
	}

	public GamePieces getPieceOf(int coordinateX, int coordinateY) {
		return this.grid[coordinateX][coordinateY];
	}

	public boolean haveAPieceIn(int coordinateX, int coordinateY) {
		return this.grid[coordinateX][coordinateY] != null;
	}

	public boolean haveFreeSpace() {
		boolean haveSpace = false;

		for (int x = 0; x < this.width; x++) {
			for (int y = 0; y < this.height; y++) {
				if (this.grid[x][y] == null) {
					haveSpace = true;
					return haveSpace;
				}
			}
		}

		return haveSpace;
	}

	public boolean anyRowHaveTheSamePiece() {
		boolean areTheSame = false;
		GamePieces[] piecesInARow = new GamePieces[this.getWidth()];
		for (int row = 0; row < this.grid.length; row++) {
			for (int column = 0; column < this.grid[row].length; column++) {
				piecesInARow[row] = getPieceOf(row, column);
			}
			areTheSame = areTheSamePieceIn(piecesInARow);

			if (areTheSame) {
				return true;
			}
		}
		return areTheSame;
	}

	public boolean anyColumnHaveTheSamePiece() {
		boolean areTheSamePiece = false;
		GamePieces[] piecesInAColumn = new GamePieces[this.getHeight()];

		for (int column = 0; column < this.grid[0].length; column++) {
			for (int row = 0; row < this.grid.length; row++) {
				piecesInAColumn[column] = getPieceOf(row, column);
			}
			areTheSamePiece = areTheSamePieceIn(piecesInAColumn);
			if (areTheSamePiece) {
				return true;
			}
		}
		return areTheSamePiece;
	}

	public boolean anyDiagonalHaveTheSamePiece() {
		boolean areTheSamePiece = false;
		GamePieces[] piecesInADiagonal = new GamePieces[this.getHeight()];

		for (int i = 0; i < this.grid.length; i++) {
			piecesInADiagonal[i] = this.grid[i][i];
		}

		areTheSamePiece = areTheSamePieceIn(piecesInADiagonal);

		if (areTheSamePiece) {
			return true;
		} else {
			for (int i = 0; i < this.getHeight(); i++) {
				piecesInADiagonal[i] = this.grid[i][this.grid.length -1 -i];
			}
			areTheSamePiece = areTheSamePieceIn(piecesInADiagonal);
		}
		return areTheSamePiece;
	}

	private boolean areTheSamePieceIn(GamePieces[] piecesVector) {
		return Arrays.stream(piecesVector).allMatch(val -> val == GamePieces.X || val == GamePieces.O);
	}
}
