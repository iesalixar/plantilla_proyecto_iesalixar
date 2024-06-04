class ActivityDTO {
    constructor(type, duration, date, place, likes, creator, image) {
        this.id = id;
        this.type = type;
        this.duration = duration;
        this.date = date;
        this.place = place;
        this.likes = likes;
        this.creator = creator;
        this.image = image;
        this.comments = [];
        this.participants = [];
    }
}