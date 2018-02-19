package com.shiva.license.controller;

import javax.servlet.http.*;

// Ư�� ����Ͻ� ��û���� �����ϰ� ��� ���� ActionForward Ÿ������
// ��ȯ�ϴ� �޼��尡 ���ǵǾ� �ִ�.
// Action : �������̽� ��
// ActionForward : ��ȯ��

public interface Action {
	public ActionForward execute(HttpServletRequest request,
								 HttpServletResponse response)
	throws Exception;
}
