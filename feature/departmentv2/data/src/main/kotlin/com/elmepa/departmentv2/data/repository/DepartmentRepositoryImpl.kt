package com.elmepa.departmentv2.data.repository

import com.elmepa.departmentv2.domain.model.DepartmentMember
import com.elmepa.departmentv2.domain.model.DepartmentModule
import com.elmepa.departmentv2.domain.model.DepartmentProgramme
import com.elmepa.departmentv2.domain.model.DepartmentResponse
import com.elmepa.departmentv2.domain.model.FeaturedItem
import com.elmepa.departmentv2.domain.model.SocialLink
import com.elmepa.departmentv2.domain.repository.DepartmentRepository
import com.stathis.domain.model.DomainResult
import javax.inject.Inject

internal class DepartmentRepositoryImpl @Inject constructor() : DepartmentRepository {

    @SuppressWarnings("LongMethod", "MaxLineLength")
    override suspend fun getDepartmentScreenInfo(): DomainResult<DepartmentResponse> {
        val dummyDepartmentInfoResponse = DepartmentResponse(
            featured = listOf(
                FeaturedItem(
                    imageUrl = "https://images.pexels.com/photos/35298823/pexels-photo-35298823.jpeg",
                    title = "Innovating for the Future",
                    description = "Discover how our department is shaping the future through research & collaboration."
                ),
                FeaturedItem(
                    imageUrl = "https://images.pexels.com/photos/35298823/pexels-photo-35298823.jpeg",
                    title = "Student Life",
                    description = "Explore the opportunities, activities, and experiences available to our students."
                ),
                FeaturedItem(
                    imageUrl = "https://images.pexels.com/photos/35298823/pexels-photo-35298823.jpeg",
                    title = "Our Community",
                    description = "Learn more about our vibrant academic community and the people who make it special."
                )
            ),
            members = listOf(
                DepartmentMember(
                    imageUrl = "https://mst.hmu.gr/wp-content/uploads/2020/05/ioannis-kopanakis.jpeg",
                    fullName = "Γιάννης Κοπανάκης",
                    profession = "Καθηγητής"
                ),
                DepartmentMember(
                    imageUrl = "https://mst.hmu.gr/wp-content/uploads/2020/05/lemonakis-christos.jpg",
                    fullName = "Χρήστος Λεμονάκης",
                    profession = "Καθηγητής"
                ),
                DepartmentMember(
                    imageUrl = "https://mst.hmu.gr/wp-content/uploads/2020/05/costas-panagiotakis.jpg",
                    fullName = "Κώστας Παναγιωτάκης",
                    profession = "Καθηγητής | Πρόεδρος Τμήματος"
                ),
                DepartmentMember(
                    imageUrl = "https://mst.hmu.gr/wp-content/uploads/2020/04/drsteliospapadakis.jpg",
                    fullName = "Στέλιος Παπαδάκης",
                    profession = "Καθηγητής"
                )
            ),
            modules = listOf(
                DepartmentModule(
                    title = "Επιστήμη των Δεδομένων & Τεχνολογίες Πληροφορικής",
                    description = "Data Science & Information Technology"
                ),
                DepartmentModule(
                    title = "Διοίκηση Επιχειρήσεων & Οργανισμών",
                    description = "Business & Organization Management"
                ),
                DepartmentModule(
                    title = "Ψηφιακό Μάρκετινγκ και Επικοινωνία",
                    description = "Digital Marketing & Communication"
                )
            ),
            programmes = listOf(
                DepartmentProgramme(
                    title = "BSc Computer Science",
                    description = "A comprehensive undergraduate programme covering the foundations and applications of CS."
                ),
                DepartmentProgramme(
                    title = "MSc Software Engineering",
                    description = "An advanced programme focused on modern software engineering practices."
                ),
                DepartmentProgramme(
                    title = "PhD in Computer Science",
                    description = "A research-focused programme for students pursuing advanced study to computer science."
                )
            ),

            social = listOf(
                SocialLink(name = "Facebook"),
                SocialLink(name = "Instagram"),
                SocialLink(name = "LinkedIn"),
                SocialLink(name = "YouTube")
            )
        )

        return DomainResult.Success(data = dummyDepartmentInfoResponse)
    }
}
