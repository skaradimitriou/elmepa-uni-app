package com.stathis.data.util

import androidx.datastore.preferences.core.longPreferencesKey

const val NEWS_URL = "https://mst.hmu.gr/news_gr/"
const val EVENTS_URL = "https://mst.hmu.gr/events/"

const val GOOGLE_URL = "https://www.google.com"
const val RESPONSE_OK = 200

const val ARTICLE = "article.et_pb_post"
const val IMG_HTML_TAG = "a.entry-featured-image-url"
const val DIV_CONTENT = "div.et_pb_post_content_0_tb_body"
const val IMG_TYPE = "img"
const val IMG_SOURCE = "src"
const val TITLE_HTML_TAG = "h2.entry-title"
const val TITLE_TYPE = "h2"
const val PARAGRAPH_HTML_TAG = "p.post-meta"
const val SPAN = "span.published"
const val URL_HTML_TAG = "h2.entry-title"
const val URL_TYPE = "a"
const val URL_ATTR = "href"

/**
 * Database constant values.
 */

const val STUDENTS_DB_PATH = "students"
const val DEPT_DB_PATH = "department"
const val SCREEN_DATA = "screen_data"
const val RESEARCH_DB_PATH = "research_in_dept"
const val UNDERGRADUATE_SYLLABUS_DB_PATH = "undergraduate_lessons"
const val POSTGRADUATE_SYLLABUS_DB_PATH = "postgraduate_lessons"
const val SYLLABUS_RULES = "syllabus_rules"
const val SEMESTER = "semester"
const val NAME = "name"
const val PROGRAMME_TYPE = "programmeType"
const val CONTACT_DB_PATH = "contact"
const val PERSONNEL_DB_PATH = "personnel"
const val FULLNAME = "fullName"
const val FAQ_DB_PATH = "faqs"
const val FAQ_ORDER_BY_FIELD = "seq"

/**
 * DataStore constant values
 */

const val SETTINGS = "settings"
const val ANNOUNCEMENTS_UPDATE_TIME = "announcements_update_time"
const val EVENTS_UPDATE_TIME = "events_update_time"
val ANNOUNCEMENTS_DS_KEY = longPreferencesKey(ANNOUNCEMENTS_UPDATE_TIME)
val EVENTS_DS_KEY = longPreferencesKey(EVENTS_UPDATE_TIME)