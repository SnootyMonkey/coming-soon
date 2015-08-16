# Change Log

All notable changes to this project will be documented in this file. This project adheres to [Semantic Versioning](http://semver.org/). This change log follows the conventions of [keepachangelog.com](http://keepachangelog.com).

## v0.3.0-SNAPSHOT - [code](https://github.com/SnootyMonkey/posthere.io/compare/v0.2...HEAD)

### Added
* Added OpenCompany.io link and screenshot in README

### Changed
* Show contacts in newest first order on the admin page
* Move from deprecated cross-overs to the new hotness, cljc
* Upgraded the Bootstrap used in the default template to 3.3.5
* Upgraded the Font Awesome used in the default template to 4.4.0
* Upgraded Clojure to 1.7.0
* Updated `build` alias to perform a complete production build
* Updated dependencies

### Fixed
* Minute in contact timestamp now has prefixed 0 on admin page
* Added explicit AOT to remove leiningen uberjar warning
* Updated the ClojureScript coming-soon namespace to remove warning
* Google fonts are now pulled in with the current protocol (HTTP or HTTPS)

### Removed
* Removed incomplete browser tests
* Custom styling of admin page, without dedicated styles this was too often ugly and hard to read

## [v0.2.0](https://github.com/SnootyMonkey/coming-soon/releases/tag/v0.2.0) -  2015-05-14 - [code](https://github.com/SnootyMonkey/posthere.io/compare/v0.1...v0.2)

### Added
* Added support for a title on all the user configured "social" links (Twitter, Facebook, GitHub, RSS)
* Added unit tests and continuous integration with Travis CI
* Added a configurable error message if saving user's contact info is not successful. :error and :error-color in your config.edn file

### Changed
* Upgraded the Font Awesome used in the default template to 3.2.1
* Moved expectations tests to midje
* Updated dependencies

## [v0.1.0](https://github.com/SnootyMonkey/coming-soon/releases/tag/v0.1.0) - 2015-05-13

Initial release.
