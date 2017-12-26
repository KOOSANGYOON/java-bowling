package bowling;

import static bowling.Status.MISSORNORMAL;

import java.util.ArrayList;

public class FinalFrame extends Frame {
	private ArrayList<String> finalScore = new ArrayList<String> ();
	
	public FinalFrame() {
		super();
		for (int i = 0; i < 3; i++) {
			finalScore.add("");
		}
	}

	@Override
	protected boolean isNotEnd() {
		return doNotExtraShot() || isFirstShotStrike();
	}
	
	@Override
	protected String changeFormat() {
		String strScore = "" + frame.get(0);
		finalScore.set(0, convertStrike(strScore));
		
		if (isPinClear()) {
			finalScore.set(frame.size() - 1, isFirstOrNot(checkStrikeOrSpare()));
			return makeString(finalScore);
		}
		if (getStatus() == MISSORNORMAL || frame.size() == 3) {
			strScore = checkMissOrNormal(frame.get(frame.size() - 1));
			finalScore.set(frame.size() - 1, isFirstOrNot(strScore));
			
		}
		return makeString(finalScore);
	}
	
	@Override
	protected String isFirstOrNot(String convertedScore) {
		if (frame.size() > 1) {
			return "|" + convertedScore;
		}
		return convertedScore;
	}
	
	private String makeString(ArrayList<String> result) {
		String finalResult = "";
		for (int i = 0; i < result.size(); i++) {
			finalResult += result.get(i);
		}
		return finalResult;
	}
	
	private boolean isFirstShotStrike() {
		return frame.get(0) == 10 && frame.size() < 3;
	}
	
	private boolean doNotExtraShot() {
		return getStatus() != Status.MISSORNORMAL && getStatus() != null;
	}
	
	private String convertStrike(String score) {
		if (score.equals("10")) {
			return "X";
		}
		return "" + score;
	}
	
	private String checkStrikeOrSpare() {
		if (frame.get(frame.size() - 1) != 10) {
			return "/";
		}
		return "X";
	}
}