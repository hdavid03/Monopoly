package game_elements.table_fields.property_fields;

public enum Color {

    BLUE(400,20, 30, 200, 300, 700, 780, 900),
    BROWN(60,10, 20, 50, 150, 450, 625, 750),
    GREEN(300,20, 30, 200, 300, 700, 780, 900),
    ORANGE(180,20, 30, 200, 300, 700, 780, 900),
    PURPLE(150,20, 30, 200, 300, 700, 780, 900),
    RED(220,20, 30, 200, 300, 700, 780, 900),
    WHITE(100,20, 30, 200, 300, 700, 780, 900),
    YELLOW(260,20, 30, 200, 300, 700, 780, 900);

    public final int price;
    public final int rent;
    public final int rentWithFullColor;
    public final int rentWith1House;
    public final int rentWith2House;
    public final int rentWith3House;
    public final int rentWith4House;
    public final int rentWithHotel;

    Color(int price, int rent, int rentWithFullColor, int rentWith1House, int rentWith2House, int rentWith3House, int rentWith4House, int rentWithHotel) {
        this.price = price;
        this.rent = rent;
        this.rentWithFullColor = rentWithFullColor;
        this.rentWith1House = rentWith1House;
        this.rentWith2House = rentWith2House;
        this.rentWith3House = rentWith3House;
        this.rentWith4House = rentWith4House;
        this.rentWithHotel = rentWithHotel;
    }
}
