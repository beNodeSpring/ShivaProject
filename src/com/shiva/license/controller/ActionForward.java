package com.shiva.license.controller;

// ActionForward Ŭ������ Action �������̽����� ����� �����ϰ� ��� ����
// ������ ������ �� �� ���Ǵ� Ŭ���� �Դϴ�.
// �� Ŭ������ Redirect ���� ���� ������ �� �������� ��ġ�� ������ �ֽ��ϴ�.
// �� ������ FrontController���� Action Forward Ŭ���� Ÿ������ ��ȯ
// ���� �������� �� ���� Ȯ���Ͽ� �ش��ϴ� ��û �������� ������ ó���� �մϴ�.

public class ActionForward {
	private boolean redirect = false;
	private String path = null;
	
	//property redirect �� is �޼ҵ�
	public boolean isRedirect() {
		//������Ƽ�� Ÿ���� boolean �� ��� get ��� is �� �տ� ���� �� �ִ�.
		return redirect;
	}
	
	// property redirect �� set �޼ҵ�
	public void setRedirect(boolean b) {
		redirect = b;
	}
	
	// property path�� get �޼ҵ�
	public String getPath() {
		return path;
	}
	
	// property path�� set �޼ҵ�
	public void setPath(String string) {
		path = string;
	}
}