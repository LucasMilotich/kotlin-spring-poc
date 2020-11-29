package com.poc.model

import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.OneToOne
@Entity
data class DomainWithMoreRelations(
    val value: String = UUID.randomUUID().toString()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    @OneToOne
    @Cascade(CascadeType.ALL)
    val relation = Relation()
    @OneToOne
    @Cascade(CascadeType.ALL)
    val relation2 = Relation2()

    @OneToMany
    @Cascade(CascadeType.ALL)
    val relation3: List<Relation2> = listOf(Relation2(),Relation2())
}