package com.example.picobotella7.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.picobotella7.model.Challenge

@Dao
interface ChallengeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveChallenge(challenge: Challenge)

    @Query("SELECT * FROM Challenge")
    suspend fun getListChallenge(): MutableList<Challenge>

    @Query ("DELETE FROM Challenge WHERE id= :iDChallenge")
    suspend fun deleteChallenge(iDChallenge:Int)

    @Query("SELECT * FROM Challenge WHERE id = :iDChallenge")
    fun selectChallenge(iDChallenge: Int): Challenge
    @Query ("UPDATE Challenge set challengetext= :newChallengetext WHERE id= :iDChallenge")
    suspend fun updateChallenge(iDChallenge:Int, newChallengetext:String)
}