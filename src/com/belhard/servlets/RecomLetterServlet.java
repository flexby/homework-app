package com.belhard.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.belhard.utils.ContentWritter;
import com.belhard.utils.DocReader;
import com.belhard.utils.StringUtils;

public class RecomLetterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String NAME_PARAM = "txt_name";
	private static final String SURNAME_PARAM = "txt_surname";
	private static final String MIDDLE_NAME_PARAM = "txt_middle_name";
	private static final String START_DATE_PARAM = "start_date";
	private static final String FINISH_DATE_PARAM = "finish_date";
	private static final String THEMES_PARAM = "checkboxes";
	private static final String PROJECT_NAME_PARAM = "projectName";
	private static final String RATING_PARAM = "rating";
	private static final String TEACHER_NAME_PARAM = "txt_teatcher_name";
	private static final String TEACHER_SURNAME_PARAM = "txt_teatcher_surname";
	private static final String TEACHER_MIDDLE_NAME_PARAM = "txt_teatcher_middle_name";
	private static final String ARCHIVATION_PARAM = "arhivation";
	private static final String ARCHIVATION_TYPE_PARAM = "archive";

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HashMap<String, String> map = new HashMap<String, String>();
		// HashMap for response-template
		request.setCharacterEncoding("UTF-8");
		map.put("{name}", StringUtils.textFieldValidator(request.getParameter(NAME_PARAM)));
		map.put("{surname}", StringUtils.textFieldValidator(request.getParameter(SURNAME_PARAM)));
		map.put("{middle_name}", StringUtils.textFieldValidator(request.getParameter(MIDDLE_NAME_PARAM)));
		map.put("{start_date}", request.getParameter(START_DATE_PARAM));
		map.put("{finish_date}", request.getParameter(FINISH_DATE_PARAM));
		map.put("{additional_topic_name}", StringUtils.themesToString(request.getParameterValues(THEMES_PARAM)));
		map.put("{project_name}", StringUtils.textFieldValidator(request.getParameter(PROJECT_NAME_PARAM)));
		map.put("{rating}", StringUtils.listFieldValidator(request.getParameter(RATING_PARAM)));
		map.put("{teacher_name}", StringUtils.textFieldValidator(request.getParameter(TEACHER_NAME_PARAM)));
		map.put("{teacher_surname}", StringUtils.textFieldValidator(request.getParameter(TEACHER_SURNAME_PARAM)));
		map.put("{teacher_middle_name}",StringUtils.textFieldValidator(request.getParameter(TEACHER_MIDDLE_NAME_PARAM)));

		ServletOutputStream outputStream = response.getOutputStream();

		response.setCharacterEncoding("UTF-8");
		
		if (request.getParameter(ARCHIVATION_TYPE_PARAM) != null
				&& request.getParameter(ARCHIVATION_TYPE_PARAM).equals("zip")) {
			response.setContentType("application/zip");
			response.setHeader("Content-Disposition", "inline; filename=\"zipfile.zip\"");
		} else if (request.getParameter(ARCHIVATION_TYPE_PARAM) != null
				&& request.getParameter(ARCHIVATION_TYPE_PARAM).equals("jar")) {
			response.setHeader("Content-Disposition", "inline; filename=\"jarfile.jar\"");
			response.setContentType("application/jar");
		} else {
			response.setHeader("Content-Disposition", "inline; filename=\"letter.docx\"");
			response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		}

		ServletContext servletContext = getServletContext();
		String contentPath = servletContext.getRealPath("");
		DocReader.SendFileToStream(DocReader.readDocxFile(contentPath + "/recommendation/document.xml", map),
				outputStream, contentPath, request.getParameter(ARCHIVATION_TYPE_PARAM));

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
