package com.likhith;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents the Alien entity mapped to the "alien_data" table in the database.
 * This class is used by Hibernate to perform ORM (Object-Relational Mapping).
 */
@Entity
@Table(name = "alien_data")
public class Alien {

    /**
     * The unique identifier (Primary Key) for the Alien entity.
     * Mapped to the "a_id" column in the database.
     */
    @Id
    @Column(name = "a_id")
    private int aid;

    /**
     * The name of the alien.
     * Mapped to the "a_name" column in the database.
     */
    @Column(name = "a_name")
    private String aname;

    /**
     * The primary technology the alien works with.
     * Maps perfectly to a column named "tech" by default if @Column is omitted.
     */
    private String tech;

    /**
     * Gets the unique identifier for this alien.
     * 
     * @return the alien's ID
     */
    public int getAid() {
        return aid;
    }

    /**
     * Sets the unique identifier for this alien.
     * 
     * @param aid the ID to set
     */
    public void setAid(int aid) {
        this.aid = aid;
    }

    /**
     * Gets the name of the alien.
     * 
     * @return the alien's name
     */
    public String getAname() {
        return aname;
    }

    /**
     * Sets the name of the alien.
     * 
     * @param aname the name to set
     */
    public void setAname(String aname) {
        this.aname = aname;
    }

    /**
     * Gets the technology associated with the alien.
     * 
     * @return the alien's primary technology
     */
    public String getTech() {
        return tech;
    }

    /**
     * Sets the technology associated with the alien.
     * 
     * @param tech the technology to set
     */
    public void setTech(String tech) {
        this.tech = tech;
    }

    /**
     * Returns a string representation of the Alien object, useful for debugging.
     * 
     * @return a formatted string containing the object's field values
     */
    @Override
    public String toString() {
        return "Alien{" +
                "aid=" + aid +
                ", aname='" + aname + '\'' +
                ", tech='" + tech + '\'' +
                '}';
    }
}
