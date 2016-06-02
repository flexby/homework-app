package com.belhard.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.belhard.utils.ContentWritter;
import com.belhard.utils.DocReader;
import com.belhard.utils.StringUtils;

public class FormTaskServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String NAME_PARAM = "txt_name";
	private static final String SURNAME_PARAM = "txt_surname";
	private static final String MIDDLE_NAME_PARAM = "txt_middle_name";
	private static final String PASSPHRASE_PARAM = "txt_passphrase";
	private static final String AGE_PARAM = "txt_age";
	private static final String SEX_PARAM = "sex";
	private static final String COURSE_PARAM = "course";
	private static final String TEACHER_PARAM = "teacher";
	private static final String RATING_PARAM = "rating";
	private static final String ANOTHER_COURSE_PARAM = "anotherCourse";
	private static final String COURSE_INFO_PARAM = "courseInfo";
	private static final String COURSE_INFO_TEXT_PARAM = "infoText";

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HashMap<String, String> map = new HashMap<String, String>();
		// HashMap for response-template
		request.setCharacterEncoding("UTF-8");

		map.put("&{name}", StringUtils.textFieldValidator(request.getParameter(NAME_PARAM)));
		map.put("&{surname}", StringUtils.textFieldValidator(request.getParameter(SURNAME_PARAM)));
		map.put("&{middle_name}", StringUtils.textFieldValidator(request.getParameter(MIDDLE_NAME_PARAM)));
		map.put("&{passphrase}", StringUtils.textFieldValidator(request.getParameter(PASSPHRASE_PARAM)));
		map.put("&{age}", StringUtils.ageFieldValidator(request.getParameter(AGE_PARAM)));
		map.put("&{course}", StringUtils.listFieldValidator(request.getParameter(COURSE_PARAM)));
		map.put("&{sex}", request.getParameter(SEX_PARAM));
		map.put("&{teacher}", StringUtils.listFieldValidator(request.getParameter(TEACHER_PARAM)));
		map.put("&{rating}", StringUtils.listFieldValidator(request.getParameter(RATING_PARAM)));
		map.put("&{anotherCourse}", StringUtils.listFieldValidator(request.getParameter(ANOTHER_COURSE_PARAM)));
		map.put("&{courseInfo}", StringUtils.infoListFieldValidator(request.getParameterValues(COURSE_INFO_PARAM)));
		map.put("&{infoText}", StringUtils.additionalTextValidator(request.getParameter(COURSE_INFO_TEXT_PARAM),
				StringUtils.infoListFieldValidator(request.getParameterValues(COURSE_INFO_PARAM))));

		response.setContentType("text/html");

		ServletContext servletContext = getServletContext();
		String tmpPath = servletContext.getRealPath("");

		ContentWritter writter = new ContentWritter(tmpPath + "/html/template.html");
		PrintWriter out = response.getWriter();
		writter.writeContent(out, map);
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
	public void init() throws ServletException {
		super.init();
	}

	@Override
	public void destroy() {
		super.destroy();
	}
}
