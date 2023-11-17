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
    suspend fun deleteChallenge(iDChallenge: Int){
        withContext(Dispatchers.IO){
            ChallengeDao.deleteChallenge(iDChallenge)
        }
    }
    suspend fun selectChallenge(challengeId: Int): Challenge? {
        return withContext(Dispatchers.IO) {
            ChallengeDao.selectChallenge(challengeId)
        }
    }
    suspend fun count() {
        val challengeCount = ChallengeDao.countChallenges()
        println("Número total de desafíos: $challengeCount")
    }
    suspend fun updateChallenge(iDChallenge:Int, newChallengetext:String){
        withContext(Dispatchers.IO){
            ChallengeDao.updateChallenge(iDChallenge,newChallengetext)
        }
    }
}