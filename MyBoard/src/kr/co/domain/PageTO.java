package kr.co.domain;

import java.util.List;

public class PageTO {

	private int curPage = 1;
	private int perPage = 10;
	private int perLine = 10;
	private int amount;
	private int totalPage;
	private int startNum;
	private int endNum;
	private List<BoardDTO> list;
	private int beginLineNum;
	private int stopLineNum;
	
	public PageTO() {
		all();
	}

	public PageTO(int curPage) {
		super();
		this.curPage = curPage;
		all();
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
		all();
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
		all();
	}

	public int getPerLine() {
		return perLine;
	}

	public void setPerLine(int perLine) {
		this.perLine = perLine;
		all();
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
		all();
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public int getEndNum() {
		return endNum;
	}

	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}

	public List<BoardDTO> getList() {
		return list;
	}

	public void setList(List<BoardDTO> list) {
		this.list = list;
	}

	public int getBeginLineNum() {
		return beginLineNum;
	}

	public void setBeginLineNum(int beginLineNum) {
		this.beginLineNum = beginLineNum;
	}

	public int getStopLineNum() {
		return stopLineNum;
	}

	public void setStopLineNum(int stopLineNum) {
		this.stopLineNum = stopLineNum;
	}
	
	private void all() {
		// 종속적 변수가 아닌 독립변수들의 set 메서드에 호출해준다
		// 독립변수 : amount, perPage, curPage, preLine 
		// 독립변수의 값이 변경되면 덩달아 값이 변경되는 변수 = 종속변수
		// 종속변수는 어짜피 독립변수들의 값이 바뀌면 자기 자신이 가진 값도 변경되기 때문에 
		// 따로 all메서드를 호출 할 필요가 없다
		totalPage = (amount-1)/perPage +1;
		// totalPage 는 amount와 perPage에 의해 결정된다 : 종속변수

		startNum = (curPage -1)*perPage +1;
		// startNum은 curPage와 perPage에 의해 결정된다 : 종속변수
		endNum = curPage * perPage;
		
		if(endNum > amount){
		  endNum = amount;
		}
		// endNum은 curPage와 perPage와 amount에 의해 결정된다 : 종속변수

		beginLineNum = ((curPage-1) / perLine) * perLine  +1;
		// beginLineNum은 curPage와 perLine 에 의해 결정된다 : 종속변수
		stopLineNum = beginLineNum +(perLine-1);
		
		if(stopLineNum > totalPage){
		  stopLineNum = totalPage;
		}
		// stopLineNum은 beginLineNum, preLine, totalPage에 의해 결정된다 : 종속변수
		
		// 독립변수인 것은 ? amount / perPage / curPage / perLine
		// 따라서 all()은 독립변수의 set메서드와 생성자에서만 호출해주면 된다
		// 독립변수의 set()에 all()을 호출해주면, 독립변수가 값을 가짐과 동시에
		// 종속변수들도 독립변수가 갖는 값에 따라 값을 가지게 된다
		// 생성자에 all()을 호출하는 이유? 객체를 새로 생성하면서 값을 가질 수 있도록 하기 위해
	}
	
	
}
