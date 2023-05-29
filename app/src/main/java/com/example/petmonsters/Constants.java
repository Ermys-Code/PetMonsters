package com.example.petmonsters;

public class Constants {
    //-- Symbols --//
    public static final String coma = ",";
    public static final String spaced_coma = ", ";
    public static final String slash = "/";
    public static final String at = "@";
    public static final String doblequote = "\"";

    //-- Utilities --//
    public static final String hash_algorithm = "MD5";
    public static final String delete_last_character_regex = ".$";
    public static final String identifier_type_drawable = "drawable";
    public static final String next_activity_key = "nextActivity";

    //-- Api --//
    public static final String api_url = "https://eu-west-2.aws.data.mongodb-api.com/app/data-okkbs/endpoint/data/v1/action/";
    public static final String method_post = "POST";
    public static final String header_content_type = "Content-Type";
    public static final String header_application_json = "application/json";
    public static final String header_access_control = "Access-Control-Request-Headers";
    public static final String header_all = "Access-Control-Request-Headers";
    public static final String header_api_key = "api-key";
    public static final String api_key = "sucE6UfBivnhG4Iwg6sNPhluANRUqrDWApBAxpV7hG0SKOYmWWBXBlRcni1ehqOU";

    //-- Querys --//
    public static final String query_create_user = "{\"collection\":\"user\", \"database\":\"ClubNoPenguin\", \"dataSource\":\"ClubNoPenguin\", \"document\":{";
    public static final String query_create_user_end = "}}";
    public static final String query_read_user = "{\"collection\":\"user\", \"database\":\"ClubNoPenguin\", \"dataSource\":\"ClubNoPenguin\", \"filter\":{\"username\":\"";
    public static final String query_read_user_end = "\"}}";
    public static final String query_update_user = "{\"collection\":\"user\", \"database\":\"ClubNoPenguin\", \"dataSource\":\"ClubNoPenguin\", \"filter\":{\"username\":\"";
    public static final String query_update_user_middle = "\"}, \"update\":{\"$set\":{\"items\":[";
    public static final String query_update_user_frends_middle = "\"}, \"update\":{\"$set\":{\"friends\":[";
    public static final String query_update_user_end = "]}}}";
    public static final String query_create_pet = "{\"collection\":\"pet_user\", \"database\":\"ClubNoPenguin\", \"dataSource\":\"ClubNoPenguin\", \"document\":{";
    public static final String query_create_pet_end = "}}";
    public static final String query_create_request = "{\"collection\":\"request\", \"database\":\"ClubNoPenguin\", \"dataSource\":\"ClubNoPenguin\", \"document\":{";
    public static final String query_create_request_end = "}}";


    //-- Query actions --//
    public static final String query_action_insert_one = "insertOne";
    public static final String query_action_find_one = "findOne";
    public static final String query_action_update_one = "updateOne";

    //-- Json keys --//
    public static final String json_key_inserted_id = "insertedId";
    public static final String json_key_modified_count = "modifiedCount";
    public static final String json_key_document = "document";
    public static final String json_key_name = "name";
    public static final String json_key_surname = "surname";
    public static final String json_key_email = "email";
    public static final String json_key_username = "username";
    public static final String json_key_password = "password";
    public static final String json_key_age = "age";
    public static final String json_key_money = "money";
    public static final String json_key_items = "items";
    public static final String json_key_friends = "friends";

    //-- Query documents --//
    public static final String user_document_name_field = "\"name\":\"";
    public static final String user_document_surname_field = "\", \"surname\":\"";
    public static final String user_document_email_field = "\", \"email\":\"";
    public static final String user_document_username_field = "\", \"username\":\"";
    public static final String user_document_password_field = "\", \"password\":\"";
    public static final String user_document_money_field = "\", \"money\":\"";
    public static final String user_document_items_age_field = "\", \"items\":[], \"age\":\"";
    public static final String user_document_friends_field = "\", \"friends\":[]";
    public static final String pet_document_owner_field = "\"owner\":\"";
    public static final String pet_document_sprite_field = "\", \"sprite\":\"";
    public static final String pet_document_color_field = "\", \"color\":\"";
    public static final String pet_document_shoes_hat_name_field = "\", \"shoes\":\"\", \"hat\":\"\", \"name\":\"";
    public static final String pet_document_tags_field = "\", \"tags\":[";
    public static final String pet_document_tags_field_end = "]";
    public static final String request_document_from_field = "\"from\":\"";
    public static final String request_document_to_field = "\", \"to\":\"";
    public static final String request_document_to_field_end = "\"";

    //-- Database --//
    public static final String collection_user = "user";
    public static final String user_parameter_username = "username";

    //-- Messages --//
    public static final String bad_user_password = "Bad user or password";
    public static final String email_not_valid = "Email not valid";
    public static final String age_not_valid = "Age not valid";
    public static final String username_not_valid = "Username not valid";
    public static final String username_not_available = "Username not available";
    public static final String password_not_valid = "Password not valid";
    public static final String name_not_valid = "Name not valid";
    public static final String surname_not_valid = "Surname not valid";
    public static final String title_not_valid = "Title not valid";
    public static final String message_not_valid = "Message not valid";
    public static final String no_documents_found = "No matching documents found";
    public static final String note_deleted_succesfully = "Note deleted succesfully";
    public static final String error_deleting_note = "Error deleting note";
    public static final String pet_created_succesfully = "Pet created succesfully";
    public static final String error_creating_pet = "Error creating pet";
    public static final String note_posted = "Note posted";
    public static final String error_posting_note = "Error posting note";
    public static final String failed_create_user = "Failed to create user";
    public static final String user_created = "User created succesfully";
    public static final String unable_to_send_request = "Unable to send request. Make sure this user exists";
    public static final String friend_request_sent = "Friend request sent";
    public static final String item_saved = "Item saved";
    public static final String item_purchased = "Item purchased";
    public static final String item_not_saved = "Item not saved";
    public static final String item_not_purchased = "Item not purchased";

    //-- Fragments --//
    public static final String params_1 = "param1";
    public static final String params_2 = "param2";

    //-- Pet names --//
    public static final String pet_name_e = "e";
    public static final String pet_name_bea = "bea";
    public static final String pet_name_bob = "bob";
    public static final String pet_name_carlos = "carlos";

    //-- Pet sprites --//
    public static final String pet_sprite_e_0 = "e_0";
    public static final String pet_sprite_e_1 = "e_1";
    public static final String pet_sprite_e_2 = "e_2";
    public static final String pet_sprite_e_3 = "e_3";
    public static final String pet_sprite_bea_0 = "bea_0";
    public static final String pet_sprite_bea_1 = "bea_1";
    public static final String pet_sprite_bea_2 = "bea_2";
    public static final String pet_sprite_bea_3 = "bea_3";
    public static final String pet_sprite_bob_0 = "bob_0";
    public static final String pet_sprite_bob_1 = "bob_1";
    public static final String pet_sprite_bob_2 = "bob_2";
    public static final String pet_sprite_bob_3 = "bob_3";
    public static final String pet_sprite_carlos_0 = "carlos_0";
    public static final String pet_sprite_carlos_1 = "carlos_1";
    public static final String pet_sprite_carlos_2 = "carlos_2";
    public static final String pet_sprite_carlos_3 = "carlos_3";
    
}
