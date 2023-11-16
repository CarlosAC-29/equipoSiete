package com.example.picobotella7.repository

import com.example.picobotella7.data.ChallengeDB
import com.example.picobotella7.data.ChallengeDao
import android.content.Context
import com.example.picobotella7.model.Challenge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChallengeRepository (val context:Context ){
    private var ChallengeDao : ChallengeDao= ChallengeDB.getDatabase(context).ChallengeDao()

    suspend fun saveChallenge(challenge: Challenge){
        withContext(Dispatchers.IO){
            ChallengeDao.saveChallenge(challenge)
        }
    }
    suspend fun getListChallenge():MutableList<Challenge>{
        return withContext(Dispatchers.IO){
            ChallengeDao.getListChallenge()
        }
    }
    suspend fun deleteChallenge(challenge: Challenge){
        withContext(Dispatchers.IO){
            ChallengeDao.deleteChallenge(challenge)
        }
    }
}