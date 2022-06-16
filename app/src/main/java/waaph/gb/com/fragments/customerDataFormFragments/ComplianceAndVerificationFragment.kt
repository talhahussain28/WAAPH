package waaph.gb.com.fragments.customerDataFormFragments

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.fragment_compliance_and_verification.*
import waaph.gb.com.R
import waaph.gb.com.activities.CustomerDataFormActivity
import waaph.gb.com.utils.BaseFragment
import java.io.File
import android.content.ActivityNotFoundException
import android.provider.FontsContract.Columns.RESULT_CODE
import androidx.core.content.FileProvider
import androidx.core.provider.FontsContractCompat.Columns.RESULT_CODE


class ComplianceAndVerificationFragment : BaseFragment(),View.OnClickListener {

    private var REQUEST_CODE = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compliance_and_verification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
        initialize()
    }

    override fun linkXML(view: View?) { }

    override fun setOnClickListener() {
        next.setOnClickListener(this)
        imgCnicFront.setOnClickListener(this)
        imgCnicBack.setOnClickListener(this)
        selectPDF.setOnClickListener(this)
    }

    override fun initialize() { }

    companion object fun newInstance(){ }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE && data != null){
            text.visibility = View.GONE
            cnic.setImageBitmap(data.extras?.get("data") as Bitmap)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.next -> {
                (activity as CustomerDataFormActivity).setCurrentItem(6)
            }
            R.id.imgCnicFront -> {
                capturePhoto()
            }
            R.id.imgCnicBack -> {
                capturePhoto()
            }
            R.id.selectPDF -> {
                openDocuments()
            }
        }
    }

    private fun capturePhoto() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CODE)

    }

    private fun openDocuments(){
//        val install = Intent(Intent.ACTION_VIEW)
//        install.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
//        install.setDataAndType(Uri.fromFile(file), mimeType)
//        val uri = context?.let {
//            FileProvider.getUriForFile(
//                it,
//                context!!.applicationContext
//                    .packageName + ".provider", file)
//        }
//        install.setDataAndType(apkURI, mimeType)
//        install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
//        context.startActivity(install);
    }

    /*fun isPermissionsAllowed(): Boolean {
        return if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            false
        } else true
    }

    fun askForPermissions(): Boolean {
        if (!isPermissionsAllowed()) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this as Activity,Manifest.permission.READ_EXTERNAL_STORAGE)) {
                showPermissionDeniedDialog()
            } else {
                ActivityCompat.requestPermissions(this as Activity,arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),REQUEST_CODE)
            }
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(requestCode: Int,permissions: Array<String>,grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission is granted, you can perform your operation here
                } else {
                    // permission is denied, you can ask for permission again, if you want
                    //  askForPermissions()
                }
                return
            }
        }
    }

    private fun showPermissionDeniedDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Permission Denied")
            .setMessage("Permission is denied, Please allow permissions from App Settings.")
            .setPositiveButton("App Settings",
                DialogInterface.OnClickListener { dialogInterface, i ->
                    // send to app settings if permission is denied permanently
                    val intent = Intent()
                    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    val uri = Uri.fromParts("package", getPackageName(), null)
                    intent.data = uri
                    startActivity(intent)
                })
            .setNegativeButton("Cancel",null)
            .show()
    }*/
}