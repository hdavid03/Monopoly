package game_elements.table_fields.property_fields;

public enum PropertyFieldColor {

    BROWN(2, 10, 30, 90, 160, 250, new int[]{1}),
    BROWN_EXTRA(4, 20, 60, 180, 320, 450, new int[]{3}),
    WHITE(6, 30, 90, 270, 400, 550, new int[]{6, 8}),
    WHITE_EXTRA(8,40, 100, 300, 450, 600, new int[]{9}),
    PURPLE(10,50, 150, 450, 625, 750, new int[]{11, 13}),
    PURPLE_EXTRA(12,60, 180, 500, 700, 900, new int[]{14}),
    ORANGE(14, 70, 200, 550, 750, 950, new int[]{16, 18}),
    ORANGE_EXTRA(16, 80, 220, 600, 800, 1000, new int[]{19}),
    RED(18, 90, 250, 700, 875, 1050, new int[]{21, 23}),
    RED_EXTRA(20, 100, 300, 750, 925, 1100, new int[]{24}),
    YELLOW(22, 110, 330, 800, 975, 1150, new int[]{26, 27}),
    YELLOW_EXTRA(24, 120, 360, 850, 1025, 1200, new int[]{29}),
    GREEN(26, 130, 390, 900, 1100, 1275, new int[]{31, 32}),
    GREEN_EXTRA(28, 150, 450, 1000, 1200, 1400, new int[]{34}),
    BLUE(35, 175, 500, 1100, 1300, 1500, new int[]{37}),
    BLUE_EXTRA(50, 200, 600, 1400, 1700, 2000, new int[]{39});

    public final int rent;
    public final int rentWith1House;
    public final int rentWith2House;
    public final int rentWith3House;
    public final int rentWith4House;
    public final int rentWithHotel;
    public final int[] fieldIDs;

    PropertyFieldColor(int rent, int rentWith1House, int rentWith2House, int rentWith3House,
                       int rentWith4House, int rentWithHotel, int[] fieldIDs) {
        this.rent = rent;
        this.rentWith1House = rentWith1House;
        this.rentWith2House = rentWith2House;
        this.rentWith3House = rentWith3House;
        this.rentWith4House = rentWith4House;
        this.rentWithHotel = rentWithHotel;
        this.fieldIDs = fieldIDs;
    }
}
