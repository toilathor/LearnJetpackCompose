package com.toilathor.lockscreen

import android.app.admin.DevicePolicyManager
import android.content.Context
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import androidx.core.content.ContextCompat.getSystemService
import androidx.glance.action.actionStartActivity

class MyQSTileService : TileService() {
    override fun onTileAdded() {
        // Khi tile được thêm vào Quick Settings lần đầu
        super.onTileAdded()
        qsTile.state = Tile.STATE_INACTIVE
        qsTile.updateTile()
    }

    override fun onStartListening() {
        // Khi người dùng nhìn thấy tile
        super.onStartListening()
        // Cập nhật trạng thái tile
        qsTile.state = Tile.STATE_INACTIVE
        qsTile.updateTile()
    }

    override fun onStopListening() {
        // Khi người dùng không còn thấy tile nữa
        super.onStopListening()
    }

    override fun onClick() {
        // Khi người dùng nhấn vào tile
        super.onClick()

        val deviceManger =
            getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        deviceManger.lockNow()
        // Đổi trạng thái tile
//        val tile = qsTile
//        if (tile.state == Tile.STATE_INACTIVE) {
//            tile.state = Tile.STATE_ACTIVE
//            // Thực hiện chức năng khi bật (ví dụ bật Wifi)
//        } else {
//            tile.state = Tile.STATE_INACTIVE
//            // Thực hiện chức năng khi tắt (ví dụ tắt Wifi)
//        }
//
//        // Cập nhật trạng thái của tile
//        tile.updateTile()
    }

    override fun onTileRemoved() {
        // Khi tile bị xóa khỏi Quick Settings
        super.onTileRemoved()
    }
}
