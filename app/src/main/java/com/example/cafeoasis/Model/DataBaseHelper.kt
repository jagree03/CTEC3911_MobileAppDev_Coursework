package com.example.cafeoasis.Model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import java.util.ArrayList

/* Database Config*/
private val DataBaseName = "CourseWorkDB.db"
private val ver : Int = 1

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, DataBaseName,null , ver) {

    /* Customer Table */
    private val TableName = "TCustomer"

    private val Column_ID = "CusID"
    private val Column_FullName = "CusFullName"
    private val Column_Email = "CusEmail"
    private val Column_PhoneNo = "CusPhoneNo"
    private val Column_UserName = "CusUserName"
    private val Column_Password = "CusPassword"
    private val Column_isActive = "CusIsActive"
    /*************************/

    /* Admin Table */
    private val TableName2 = "TAdmin"

    private val Column_AdminID = "AdminID"
    private val Column_AdminFullName = "AdminFullName"
    private val Column_AdminEmail = "AdminEmail"
    private val Column_AdminPhoneNo = "AdminPhoneNo"
    private val Column_AdminUserName = "AdminUserName"
    private val Column_AdminPassword = "AdminPassword"
    private val Column_AdminisActive = "AdminIsActive"
    /*************************/

    /* Product Table */
    private val TableName3 = "TProduct"

    private val Column_ProdID = "ProdID"
    private val Column_ProdName = "ProdName"
    private val Column_ProdCategory = "ProdCategory"
    private val Column_ProdPrice = "ProdPrice"
    private val Column_ProdImage = "ProdImage"
    private val Column_ProdAvailable = "ProdIsAvailable"
    /*************************/

    /* Payment Table */
    private val TableName4 = "TPayment"

    private val Column_PaymentID = "PaymentID"
    private val Column_PaymentOrderId = "OrderId"
    private val Column_PaymentType = "PaymentType"
    private val Column_Amount = "Amount"
    private val Column_PaymentDate = "PaymentDate"
    /*************************/

    /* Order Table */
    private val TableName5 = "TOrder"

    private val Column_OrderID = "OrderId"
    private val Column_OrderCusId = "CusId"
    private val Column_OrderCusFullName = "CusFullName"
    private val Column_OrderDate = "OrderDate"
    private val Column_OrderTime = "OrderTime"
    private val Column_OrderStatus = "OrderStatus"
    /*************************/

    /* OrderDetails Table */
    private val TableName6 = "TOrderDetails"

    private val Column_OrderDetailsId = "OrderDetailsId"
    private val Column_OrderDetailsOrderId = "OrderId"
    private val Column_OrderDetailsProdId = "ProdId"

    /*************************/

    /* Feedback Table */
    private val TableName7 = "TFeedback"

    private val Column_FeedbackId = "FeedbackId"
    private val Column_FeedbackCusId = "CusId"
    private val Column_FeedbackProdId = "ProdId"
    private val Column_FeedbackComment = "Comment"
    private val Column_FeedbackStarRating = "StarRating"

    /*************************/


    // This is called the first time a database is accessed
    // Create a new database
    override fun onCreate(db: SQLiteDatabase?) {
       try {
           // creates customer table in database
           val sqlCreateStatement: String = "CREATE TABLE " + TableName + " ( " + Column_ID +
                   " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                   Column_FullName + " TEXT NOT NULL, " +
                   Column_Email + " TEXT NOT NULL, " +
                   Column_PhoneNo + " TEXT NOT NULL, " +
                   Column_UserName + " TEXT NOT NULL, " +
                   Column_Password + " TEXT NOT NULL, " +
                   Column_isActive + " INT NOT NULL )"
           db?.execSQL(sqlCreateStatement)


           // creates admin table in database
           val sqlCreateStatement2: String = "CREATE TABLE " + TableName2 + " ( " + Column_AdminID +
                   " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                   Column_AdminFullName + " TEXT NOT NULL, " +
                   Column_AdminEmail + " TEXT NOT NULL, " +
                   Column_AdminPhoneNo + " TEXT NOT NULL, " +
                   Column_AdminUserName + " TEXT NOT NULL, " +
                   Column_AdminPassword + " TEXT NOT NULL, " +
                   Column_AdminisActive + " INT NOT NULL )"
           db?.execSQL(sqlCreateStatement2)

           // creates product table in database
           val sqlCreateStatement3: String = "CREATE TABLE " + TableName3 + " ( " + Column_ProdID +
                   " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                   Column_ProdName + " TEXT NOT NULL, " +
                   Column_ProdCategory + " TEXT NOT NULL, " +
                   Column_ProdPrice + " REAL NOT NULL, " +
                   Column_ProdImage + " BLOB NOT NULL, " +
                   Column_ProdAvailable + " INT NOT NULL, "
           db?.execSQL(sqlCreateStatement3)

           // creates Payment table in database
           val sqlCreateStatement4: String = "CREATE TABLE " + TableName4 + " ( " + Column_PaymentID +
                   " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                   Column_PaymentOrderId + "INT NOT NULL, " +
                   Column_PaymentType + " TEXT NOT NULL, " +
                   Column_Amount + " REAL NOT NULL, " +
                   Column_PaymentDate + " TEXT NOT NULL, "
           db?.execSQL(sqlCreateStatement4)

           // creates Order table in database
           val sqlCreateStatement5: String = "CREATE TABLE " + TableName5 + " ( " + Column_OrderID +
                   " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                   Column_OrderCusId + "INT NOT NULL, " +
                   Column_OrderCusFullName + " TEXT NOT NULL, " +
                   Column_OrderDate + " TEXT NOT NULL, " +
                   Column_OrderTime + " TEXT NOT NULL, " +
                   Column_OrderStatus + " TEXT NOT NULL, "
           db?.execSQL(sqlCreateStatement5)

           // creates OrderDetails table in database
           val sqlCreateStatement6: String = "CREATE TABLE " + TableName6 + " ( " + Column_OrderDetailsId +
                   " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                   Column_OrderDetailsOrderId + "INT NOT NULL, " +
                   Column_OrderDetailsProdId + " INT NOT NULL, "
           db?.execSQL(sqlCreateStatement6)

           // creates Feedback table in database
           val sqlCreateStatement7: String = "CREATE TABLE " + TableName7 + " ( " + Column_FeedbackId +
                   " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                   Column_FeedbackCusId + "INT NOT NULL, " +
                   Column_FeedbackProdId + " INT NOT NULL, " +
                   Column_FeedbackComment + " TEXT NOT NULL, " +
                   Column_FeedbackStarRating + " REAL NOT NULL, "
           db?.execSQL(sqlCreateStatement7)


           // now u gotta make all the tables in here and sql create statements and execute etc.
       }
       catch (e: SQLiteException) {}
    }

    // This is called if the database ver. is changed
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    /**
     * return  1 : the new use has been add to the database successfully
     * return -1 : Error, adding new user
     * return -2 : can not Open/Create database
     * return -3 : user name is already exist
     *
     */
    fun addCustomer(customer: Customer) : Int {

        val db: SQLiteDatabase
        val isUserNameAlreadyExists = checkCustomerUserName(customer) // check if the username is already exist in the database
        if(isUserNameAlreadyExists < 0)
           return isUserNameAlreadyExists

        try {
            db = this.writableDatabase
        }
        catch(e: SQLiteException) {
            return -2
        }

        val cv: ContentValues = ContentValues()

        cv.put(Column_FullName, customer.CusFullName)
        cv.put(Column_Email, customer.CusEmail)
        cv.put(Column_PhoneNo, customer.CusPhoneNo)
        cv.put(Column_UserName, customer.CusUserName)
        cv.put(Column_Password, customer.CusPassword.hashCode().toString())
        cv.put(Column_isActive, customer.CusIsActive)

        val success  =  db.insert(TableName, null, cv)

        db.close()
        if (success.toInt() == -1) return success.toInt() //Error, adding new user
        else return success.toInt() //1

    }

    fun addAdmin(admin: Admin) : Int {

        val db: SQLiteDatabase
        val isUserNameAlreadyExists = checkAdminUserName(admin) // check if the username is already exist in the database
        if(isUserNameAlreadyExists < 0)
            return isUserNameAlreadyExists

        try {
            db = this.writableDatabase
        }
        catch(e: SQLiteException) {
            return -2
        }

        val cv: ContentValues = ContentValues()

        cv.put(Column_AdminFullName, admin.AdminFullName)
        cv.put(Column_AdminEmail, admin.AdminEmail)
        cv.put(Column_AdminPhoneNo, admin.AdminPhoneNo)
        cv.put(Column_AdminUserName, admin.AdminUserName)
        cv.put(Column_AdminPassword, admin.AdminPassword.hashCode().toString())
        cv.put(Column_AdminisActive, admin.AdminIsActive)

        val success  =  db.insert(TableName2, null, cv)

        db.close()
        if (success.toInt() == -1) return success.toInt() //Error, adding new user
        else return success.toInt() //1

    }

    private fun checkCustomerUserName(customer: Customer): Int {

        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        }
        catch(e: SQLiteException) {
            return -2
        }

        val userName = customer.CusUserName.lowercase()

        val sqlStatement = "SELECT * FROM $TableName WHERE $Column_UserName = ?"
        val param = arrayOf(userName)
        val cursor: Cursor =  db.rawQuery(sqlStatement,param)

        if(cursor.moveToFirst()){
            // The user is found
            val n = cursor.getInt(0)
            cursor.close()
            db.close()
            return -3 // error the user name is already exist
        }

        cursor.close()
        db.close()
        return 0 //User not found

    }

    private fun checkAdminUserName(admin: Admin): Int {

        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        }
        catch(e: SQLiteException) {
            return -2
        }

        val userName = admin.AdminUserName.lowercase()

        val sqlStatement = "SELECT * FROM $TableName2 WHERE $Column_AdminUserName = ?"
        val param = arrayOf(userName)
        val cursor: Cursor =  db.rawQuery(sqlStatement,param)

        if(cursor.moveToFirst()){
            // The user is found
            val n = cursor.getInt(0)
            cursor.close()
            db.close()
            return -3 // error the user name is already exist
        }

        cursor.close()
        db.close()
        return 0 //User not found

    }

    fun getCustomer(customer: Customer) : Int {

        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        }
        catch(e: SQLiteException) {
            return -2
        }

        val userName = customer.CusUserName.lowercase()
        val userPassword = customer.CusPassword.hashCode().toString()
        //val sqlStatement = "SELECT * FROM $TableName WHERE $Column_UserName = $userName AND $Column_Password = $userPassword"

        val sqlStatement = "SELECT * FROM $TableName WHERE $Column_UserName = ? AND $Column_Password = ?"
        val param = arrayOf(userName,userPassword)
        val cursor: Cursor =  db.rawQuery(sqlStatement,param)
        if(cursor.moveToFirst()){
            // The user is found
            val n = cursor.getInt(0)
            cursor.close()
            db.close()
            return n
        }

        cursor.close()
        db.close()
        return -1 //User not found

    }

    fun getAdmin(admin: Admin) : Int {

        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        }
        catch(e: SQLiteException) {
            return -2
        }

        val userName = admin.AdminUserName.lowercase()
        val userPassword = admin.AdminPassword.hashCode().toString()
        //val sqlStatement = "SELECT * FROM $TableName WHERE $Column_UserName = $userName AND $Column_Password = $userPassword"

        val sqlStatement = "SELECT * FROM $TableName2 WHERE $Column_AdminUserName = ? AND $Column_AdminPassword = ?"
        val param = arrayOf(userName,userPassword)
        val cursor: Cursor =  db.rawQuery(sqlStatement,param)
        if(cursor.moveToFirst()){
            // The user is found
            val n = cursor.getInt(0)
            cursor.close()
            db.close()
            return n
        }

        cursor.close()
        db.close()
        return -1 //User not found

    }


    fun getCustomerOnlyUser(customer: Customer) : Customer {

        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        }
        catch(e: SQLiteException) {
            return customer
        }

        val userName = customer.CusUserName.lowercase()
        //val sqlStatement = "SELECT * FROM $TableName WHERE $Column_UserName = $userName AND $Column_Password = $userPassword"
        val sqlStatement = "SELECT * FROM $TableName WHERE $Column_UserName = ?"
        val param = arrayOf(userName)
        val cursor: Cursor =  db.rawQuery(sqlStatement,param)
        if(cursor.moveToFirst()){
            // The user is found
            customer.CusId = cursor.getInt(0)
            customer.CusFullName = cursor.getString(1)
            customer.CusEmail = cursor.getString(2)
            customer.CusPhoneNo = cursor.getString(3)
            customer.CusUserName = cursor.getString(4)
            customer.CusPassword = cursor.getString(5)
            return customer
        }
        cursor.close()
        db.close()
        return customer // return customer object as is
    }


    fun getProducts(category: String) : ArrayList<Product> {

        val imageList = ArrayList<Product>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $TableName3 WHERE $Column_ProdCategory = ?"

        val param = arrayOf(category)
        val cursor: Cursor =  db.rawQuery(sqlStatement,param)

        if(cursor.moveToFirst())
            do {
                val id: Int = cursor.getInt(0)
                val name: String = cursor.getString(1)
                val category: String = cursor.getString(2)
                val price: Double = cursor.getDouble(3)
                val image: ByteArray? = cursor.getBlob(4)
                val available: Int = cursor.getInt(5)
                val img = Product(id, name, category, price, image, available)
                imageList.add(img)
            }while(cursor.moveToNext())

        cursor.close()
        db.close()

        return imageList
    }

    fun getAllProducts(): CartList {

        val ProductList = CartList()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $TableName3"


        val cursor: Cursor =  db.rawQuery(sqlStatement,null)

        if(cursor.moveToFirst())
            do {
                val id: Int = cursor.getInt(0)
                val name: String = cursor.getString(1)
                val category: String = cursor.getString(2)
                val price: Double = cursor.getDouble(3)
                val image: ByteArray? = cursor.getBlob(4)
                val available: Int = cursor.getInt(5)
                val prod = Product(id, name, category, price, image, available)
                ProductList.addProduct(prod)
            }while(cursor.moveToNext())

        cursor.close()
        db.close()

        return ProductList
    }

    fun addPayment(payment: Payment) : Int {

        val db: SQLiteDatabase

        try {
            db = this.writableDatabase
        }
        catch(e: SQLiteException) {
            return -2
        }

        val cv: ContentValues = ContentValues()

        cv.put(Column_PaymentOrderId, payment.OrderId)
        cv.put(Column_PaymentType, payment.PaymentType)
        cv.put(Column_Amount, payment.Amount)
        cv.put(Column_PaymentDate, payment.PaymentDate)

        val success  =  db.insert(TableName4, null, cv)

        db.close()
        if (success.toInt() == -1) return success.toInt() //Error, adding new user
        else return success.toInt() //1

    }

    fun addOrder(order: Order) : Int {

        val db: SQLiteDatabase

        try {
            db = this.writableDatabase
        }
        catch(e: SQLiteException) {
            return -2
        }

        val cv: ContentValues = ContentValues()

        cv.put(Column_OrderCusId, order.CusId)
        cv.put(Column_OrderCusFullName, order.CusFullName)
        cv.put(Column_OrderDate, order.OrderDate)
        cv.put(Column_OrderTime, order.OrderTime)
        cv.put(Column_OrderStatus, order.OrderStatus)

        val success  =  db.insert(TableName5, null, cv)

        db.close()
        if (success.toInt() == -1) return success.toInt() //Error, adding new user
        else return success.toInt() //1

    }

    fun updateOrder(order: Order) : Int {

        val db: SQLiteDatabase

        try {
            db = this.writableDatabase
        }
        catch(e: SQLiteException) {
            return -2
        }

        val cv: ContentValues = ContentValues()

        cv.put(Column_OrderStatus, order.OrderStatus)

        val id = order.OrderId.toString()
        val param = arrayOf(id)
        val success  =  db.update(TableName5, cv, "$Column_OrderID = ?", param)

        db.close()
        if (success.toInt() == -1) return success.toInt() //Error, adding new user
        else return success.toInt() //1

    }

    fun updateProduct(p: Product) : Int {

        val db: SQLiteDatabase

        try {
            db = this.writableDatabase
        }
        catch(e: SQLiteException) {
            return -2
        }


        val cv: ContentValues = ContentValues()

        cv.put(Column_ProdName, p.ProdName)
        cv.put(Column_ProdCategory, p.ProdCategory)
        cv.put(Column_ProdPrice, p.ProdPrice)
        cv.put(Column_ProdAvailable, p.ProdAvailable)

        val id = p.ProdId.toString()
        val param = arrayOf(id)
        val success  =  db.update(TableName3, cv, "$Column_ProdID = ?", param)

        db.close()
        if (success.toInt() == -1) return success.toInt() //Error, adding new user
        else return success.toInt() //1

    }

    fun getOrderFromID(order: Order) : Order {

        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        }
        catch(e: SQLiteException) {
            return order
        }

        val id = order.OrderId.toString()
        //val sqlStatement = "SELECT * FROM $TableName WHERE $Column_UserName = $userName AND $Column_Password = $userPassword"
        val sqlStatement = "SELECT * FROM $TableName5 WHERE $Column_OrderID = ?"
        val param = arrayOf(id)
        val cursor: Cursor =  db.rawQuery(sqlStatement,param)
        if(cursor.moveToFirst()){
            // The order is found
            order.CusId = cursor.getInt(1)
            order.CusFullName = cursor.getString(2)
            order.OrderDate = cursor.getString(3)
            order.OrderTime = cursor.getString(4)
            order.OrderStatus = cursor.getString(5)
            return order
        }
        cursor.close()
        db.close()
        return order // return order object as is
    }

    fun retrieveOrder(order: Order) : Order {

        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        }
        catch(e: SQLiteException) {
            return order
        }

        val id = order.CusId.toString()
        val fullname = order.CusFullName
        val date = order.OrderDate
        val time = order.OrderTime
        val status = order.OrderStatus
        //val sqlStatement = "SELECT * FROM $TableName WHERE $Column_UserName = $userName AND $Column_Password = $userPassword"
        val sqlStatement = "SELECT * FROM $TableName5 WHERE $Column_OrderCusId = ? AND $Column_OrderCusFullName = ? AND $Column_OrderDate = ? AND $Column_OrderTime = ? AND $Column_OrderStatus = ?"
        val param = arrayOf(id, fullname, date, time, status)
        val cursor: Cursor =  db.rawQuery(sqlStatement,param)
        if(cursor.moveToFirst()){
            // The order is found
            order.OrderId = cursor.getInt(0)
            order.CusId = cursor.getInt(1)
            order.CusFullName = cursor.getString(2)
            order.OrderDate = cursor.getString(3)
            order.OrderTime = cursor.getString(4)
            order.OrderStatus = cursor.getString(5)
            return order
        }
        cursor.close()
        db.close()
        return order // return order object as is
    }


    fun addOrderDetails(orderdetails: OrderDetails) : Int {

        val db: SQLiteDatabase

        try {
            db = this.writableDatabase
        }
        catch(e: SQLiteException) {
            return -2
        }

        val cv: ContentValues = ContentValues()

        cv.put(Column_OrderDetailsOrderId, orderdetails.OrderId)
        cv.put(Column_OrderDetailsProdId, orderdetails.ProdId)

        val success  =  db.insert(TableName6, null, cv)

        db.close()
        if (success.toInt() == -1) return success.toInt() //Error, adding new user
        else return success.toInt() //1

    }

    fun addFeedback(feedback: Feedback) : Int {

        val db: SQLiteDatabase

        try {
            db = this.writableDatabase
        }
        catch(e: SQLiteException) {
            return -2
        }

        val cv: ContentValues = ContentValues()

        cv.put(Column_FeedbackCusId, feedback.CusId)
        cv.put(Column_FeedbackProdId, feedback.ProdId)
        cv.put(Column_FeedbackComment, feedback.Comment)
        cv.put(Column_FeedbackStarRating, feedback.StarRating)

        val success  =  db.insert(TableName7, null, cv)

        db.close()
        if (success.toInt() == -1) return success.toInt() //Error, adding new user
        else return success.toInt() //1

    }

    fun getAllFeedbacks(ProdId: Int) : FeedbackList {

        val fbList = FeedbackList()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $TableName7 WHERE $Column_FeedbackProdId = ?"

        val param = arrayOf(ProdId.toString())
        val cursor: Cursor =  db.rawQuery(sqlStatement,param)

        if(cursor.moveToFirst())
            do {
                val fb_id: Int = cursor.getInt(0)
                val fb_cusid: Int = cursor.getInt(1)
                val fb_prodid: Int = cursor.getInt(2)
                val fb_comment: String = cursor.getString(3)
                val fb_starRating: Float = cursor.getFloat(4)
                var record = Feedback(fb_id, fb_cusid, fb_prodid, fb_comment, fb_starRating)
                fbList.addFeedbackRecord(record)
            }while(cursor.moveToNext())

        cursor.close()
        db.close()

        return fbList
    }

    fun getAllOrders() : OrderList {

        val oList = OrderList()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $TableName5"

        val cursor: Cursor =  db.rawQuery(sqlStatement,null)

        if(cursor.moveToFirst())
            do {
                val orderID: Int = cursor.getInt(0)
                val cusID: Int = cursor.getInt(1)
                val cusFullName: String = cursor.getString(2)
                val OrderDate: String = cursor.getString(3)
                val OrderTime: String = cursor.getString(4)
                val OrderStatus: String = cursor.getString(5)
                var record = Order(orderID, cusID, cusFullName, OrderDate, OrderTime, OrderStatus)
                oList.addOrder(record)
            }while(cursor.moveToNext())

        cursor.close()
        db.close()

        return oList
    }

    fun getAllOrdersForSpecificCustomer(id: Int) : OrderList {

        val oList = OrderList()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $TableName5 WHERE $Column_OrderCusId = ?"

        val param = arrayOf(id.toString())
        val cursor: Cursor =  db.rawQuery(sqlStatement,param)

        if(cursor.moveToFirst())
            do {
                val orderID: Int = cursor.getInt(0)
                val cusID: Int = cursor.getInt(1)
                val cusFullName: String = cursor.getString(2)
                val OrderDate: String = cursor.getString(3)
                val OrderTime: String = cursor.getString(4)
                val OrderStatus: String = cursor.getString(5)
                var record = Order(orderID, cusID, cusFullName, OrderDate, OrderTime, OrderStatus)
                oList.addOrder(record)
            }while(cursor.moveToNext())

        cursor.close()
        db.close()

        return oList
    }

    fun addProduct(p: Product) : Int {

        val db: SQLiteDatabase

        try {
            db = this.writableDatabase
        }
        catch(e: SQLiteException) {
            return -2
        }

        val cv: ContentValues = ContentValues()

        cv.put(Column_ProdName, p.ProdName)
        cv.put(Column_ProdCategory, p.ProdCategory)
        cv.put(Column_ProdPrice, p.ProdPrice)
        cv.put(Column_ProdAvailable, p.ProdAvailable)

        val success  =  db.insert(TableName3, null, cv)

        db.close()
        if (success.toInt() == -1) return success.toInt() //Error, adding new user
        else return success.toInt() //1

    }

    fun removeProduct(id: Int) : Int {

        val db: SQLiteDatabase

        try {
            db = this.writableDatabase
        }
        catch(e: SQLiteException) {
            return -2
        }

        val param = arrayOf(id.toString())
        val success  =  db.delete(TableName3, "$Column_ProdID = ?", param)

        db.close()
        if (success.toInt() == -1) return success.toInt() //Error, adding new user
        else return success.toInt() //1

    }

}