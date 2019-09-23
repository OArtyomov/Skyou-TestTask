package com.skyou.config;

import springfox.documentation.spring.web.paths.AbstractPathProvider;
import springfox.documentation.spring.web.paths.RelativePathProvider;

public class AlwaysRootPathProvider extends AbstractPathProvider {
	@Override
	protected String applicationPath() {
		return RelativePathProvider.ROOT;
	}

	@Override
	protected String getDocumentationPath() {
		return RelativePathProvider.ROOT;
	}
}