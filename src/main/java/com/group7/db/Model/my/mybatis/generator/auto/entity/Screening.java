package com.group7.db.Model.my.mybatis.generator.auto.entity;

public class Screening {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Screening.ID
     *
     * @mbg.generated Wed Feb 22 19:15:59 CST 2023
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Screening.movie_id
     *
     * @mbg.generated Wed Feb 22 19:15:59 CST 2023
     */
    private Integer movieId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Screening.screen_id
     *
     * @mbg.generated Wed Feb 22 19:15:59 CST 2023
     */
    private Integer screenId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Screening.date
     *
     * @mbg.generated Wed Feb 22 19:15:59 CST 2023
     */
    private String date;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Screening.start_time
     *
     * @mbg.generated Wed Feb 22 19:15:59 CST 2023
     */
    private String startTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column Screening.ticket_sold
     *
     * @mbg.generated Wed Feb 22 19:15:59 CST 2023
     */
    private Integer ticketSold;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Screening.ID
     *
     * @return the value of Screening.ID
     *
     * @mbg.generated Wed Feb 22 19:15:59 CST 2023
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Screening.ID
     *
     * @param id the value for Screening.ID
     *
     * @mbg.generated Wed Feb 22 19:15:59 CST 2023
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Screening.movie_id
     *
     * @return the value of Screening.movie_id
     *
     * @mbg.generated Wed Feb 22 19:15:59 CST 2023
     */
    public Integer getMovieId() {
        return movieId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Screening.movie_id
     *
     * @param movieId the value for Screening.movie_id
     *
     * @mbg.generated Wed Feb 22 19:15:59 CST 2023
     */
    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Screening.screen_id
     *
     * @return the value of Screening.screen_id
     *
     * @mbg.generated Wed Feb 22 19:15:59 CST 2023
     */
    public Integer getScreenId() {
        return screenId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Screening.screen_id
     *
     * @param screenId the value for Screening.screen_id
     *
     * @mbg.generated Wed Feb 22 19:15:59 CST 2023
     */
    public void setScreenId(Integer screenId) {
        this.screenId = screenId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Screening.date
     *
     * @return the value of Screening.date
     *
     * @mbg.generated Wed Feb 22 19:15:59 CST 2023
     */
    public String getDate() {
        return date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Screening.date
     *
     * @param date the value for Screening.date
     *
     * @mbg.generated Wed Feb 22 19:15:59 CST 2023
     */
    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Screening.start_time
     *
     * @return the value of Screening.start_time
     *
     * @mbg.generated Wed Feb 22 19:15:59 CST 2023
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Screening.start_time
     *
     * @param startTime the value for Screening.start_time
     *
     * @mbg.generated Wed Feb 22 19:15:59 CST 2023
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Screening.ticket_sold
     *
     * @return the value of Screening.ticket_sold
     *
     * @mbg.generated Wed Feb 22 19:15:59 CST 2023
     */
    public Integer getTicketSold() {
        return ticketSold;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Screening.ticket_sold
     *
     * @param ticketSold the value for Screening.ticket_sold
     *
     * @mbg.generated Wed Feb 22 19:15:59 CST 2023
     */
    public void setTicketSold(Integer ticketSold) {
        this.ticketSold = ticketSold;
    }
}