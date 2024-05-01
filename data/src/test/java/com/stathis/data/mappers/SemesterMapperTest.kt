package com.stathis.data.mappers

import com.stathis.data.datasource.remote.mapper.SemesterMapper
import com.stathis.data.datasource.remote.model.SemesterDto
import com.stathis.model.syllabus.Semester
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import org.junit.Test

class SemesterMapperTest {

    @Test
    fun testNullSemesterListResponse(){
        val response : List<SemesterDto>?=null
        val mappedResponse = SemesterMapper.toDomainModel(response)
        assertNotNull(mappedResponse)
    }

    @Test
    fun testSemesterDtoListResponse(){
        val response = listOf(
            SemesterDto("1st Semester")
        )
        val mappedResult = SemesterMapper.toDomainModel(response)

        val item = mappedResult.getOrNull(0)

        assertTrue(item?.name == "1st Semester" && item is Semester)
    }
}