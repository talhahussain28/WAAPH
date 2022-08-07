package waaph.gb.com.fragments.customerDataFormFragments

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import kotlinx.android.synthetic.main.fragment_compliance_and_verification.*
import waaph.gb.com.R
import waaph.gb.com.activities.CustomerDataFormActivity
import waaph.gb.com.utils.BaseFragment
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import com.google.android.material.snackbar.Snackbar
import com.miletap.konnect.util.DocPathUtils
import kotlinx.android.synthetic.main.custom_dialog.*
import waaph.gb.com.BuildConfig
import waaph.gb.com.network.ServiceUtils
import waaph.gb.com.responses.CustomerGroupResponse
import waaph.gb.com.utils.GeneralBottomAdapter
import waaph.gb.com.utils.Utils
import java.io.File
import java.io.IOException
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import javax.security.auth.callback.Callback
import kotlin.collections.ArrayList


class ComplianceAndVerificationFragment : BaseFragment(), View.OnClickListener {

    private var REQUEST_CODE = -1
    private var REQUEST_CODEE = -2
    private var REQUEST_CO = -3
    private val permissionList = listOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    private val REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124
    private val PICK_MULTIPLE_FILE_REQUEST = 3
    private val REQUEST_CAMERA_CAPTURE = 100
    private val PICK_DOCUMENT = 200
    private var captureImageFile: File? = null
    private var captureImageUriPath: String? = null
    private var selectedImage: Uri? = null
    private var comeFrom: String = ""
    private var selectedFilePath: String? = null
    private lateinit var file: File
    private var fileName: String = ""


    private val mimeTypes: Array<String> = arrayOf(
        "application/msword",
        "application/vnd.openxmlformats-officedocument.wordprocessingml.document",//.doc & .docx
        "text/plain",
        "application/pdf",
        "application/vnd.ms-excel",
        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",//.xls & .xlsx
        "application/vnd.ms-powerpoint",
        "application/vnd.openxmlformats-officedocument.presentationml.presentation" //.ppt & .pptx
    )

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

    override fun linkXML(view: View?) {}

    override fun setOnClickListener() {
        next.setOnClickListener(this)
        imgCnicFront.setOnClickListener(this)
        imgCnicBack.setOnClickListener(this)
        cardView_upload_strn.setOnClickListener(this)
        docText.setOnClickListener(this)
        otherDoc.setOnClickListener(this)
        cardView_upload_dsl.setOnClickListener(this)
        cancel.setOnClickListener(this)
        cancelOther.setOnClickListener(this)
        cardview_upload_card.setOnClickListener(this)
        cardview_upload_degree.setOnClickListener(this)
        //selectPDF.setOnClickListener(this)
    }

    override fun initialize() {}


    companion object

    fun newInstance() {}

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE && data != null){
            text.visibility = View.GONE
            cnic.setImageBitmap(data.extras?.get("data") as Bitmap)
        }
        else if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODEE && data != null){
            tt.visibility = View.GONE
            cnicB.setImageBitmap(data.extras?.get("data") as Bitmap)
        }
        else if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CO && data != null){
            str.visibility = View.GONE
            imga.setImageBitmap(data.extras?.get("data") as Bitmap)
        }
    }*/

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.next -> {
                (activity as CustomerDataFormActivity).setCurrentItem(6)
            }
            R.id.imgCnicFront -> {
                comeFrom = "front"
                requestPermission()
                //capturePhoto()
            }
            R.id.imgCnicBack -> {
                comeFrom = "back"
                requestPermission()
                //capturePhotoBack()
            }
            R.id.docText -> {
                //openDocuments()
                comeFrom = "selectPDF"
                requestPermissionForSelectingDocument()
            }
            R.id.otherDoc -> {
                comeFrom = "selectOther"
                requestPermissionForSelectingDocument()
            }
            R.id.cardView_upload_strn -> {
                //captureStrn()
                comeFrom = "STRN"
                requestPermission()

            }
            R.id.cardView_upload_dsl -> {
                comeFrom = "DSL"
                requestPermission()
            }
            R.id.cardview_upload_card -> {
                comeFrom = "visitingCard"
                requestPermission()
            }
            R.id.cardview_upload_degree -> {
                comeFrom = "degree"
                requestPermission()
            }
            R.id.cancel -> {
                selectedFilePath = null
                // file = File(selectedFilePath)
                docText.text = "Select Document"
            }
            R.id.cancelOther -> {
                selectedFilePath = null
                otherDoc.text = "Select Document"

            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS -> {
                if (grantResults.isNotEmpty()) {
                    val camera = grantResults[0] == PackageManager.PERMISSION_GRANTED
                    val readExternalFile = grantResults[1] == PackageManager.PERMISSION_GRANTED
                    val writeExternalFile = grantResults[2] == PackageManager.PERMISSION_GRANTED

                    if (camera && readExternalFile && writeExternalFile) {
                        // Permissions are granted
                        // code here
                        pickerActionDialog()
                    } else {
                        showToast("Please Grant Permissions to upload file")
                        /*  Snackbar.make(
                              findViewById(android.R.id.content),
                              "Please Grant Permissions to upload file",
                              Snackbar.LENGTH_INDEFINITE
                          ).setAction(
                              "ENABLE"
                          ) {
                              requestPermissions(
                                  permissionList.toTypedArray(),
                                  REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS
                              )
                          }.show()*/
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQUEST_CAMERA_CAPTURE -> {
                    if (comeFrom == "front") {
                        text.visibility = View.GONE
                        cnic.setImageBitmap(data?.extras?.get("data") as Bitmap)
                        // imageClicked(cnic)
                    } else if (comeFrom == "back") {
                        tt.visibility = View.GONE
                        cnicB.setImageBitmap(data?.extras?.get("data") as Bitmap)
                        //imageClicked(cnicB)
                    } else if (comeFrom == "STRN") {
                        str.visibility = View.GONE
                        image_Strn.setImageBitmap(data?.extras?.get("data") as Bitmap)

                    } else if (comeFrom == "DSL") {
                        text_dsl.visibility = View.GONE
                        image_dsl.setImageBitmap(data?.extras?.get("data") as Bitmap)
                    } else if (comeFrom == "visitingCard") {
                        text_card.visibility = View.GONE
                        image_card.setImageBitmap(data?.extras?.get("data") as Bitmap)
                    } else if (comeFrom == "degree") {
                        text_degree.visibility = View.GONE
                        image_degree.setImageBitmap(data?.extras?.get("data") as Bitmap)
                    }
                    /* val compressImagePath =
                         captureImageUriPath?.let { Utils.compressImage(requireContext(), it) }
                     if (compressImagePath!!.isNotEmpty()) {
                         captureImageFile = File(compressImagePath)
                         text.visibility = View.GONE
                         cnic.setImageBitmap(captureImageFile as Bitmap)*/
                    /*  receiptAdapter?.addReceipt(
                          ExpenseReceiptAdapter.ExpenseReceiptModel(
                              compressImagePath,
                              Uri.fromFile(captureImageFile),
                              captureImageFile
                          )
                      )
                      if (recyclerView_receipt.visibility == View.GONE) {
                          recyclerView_receipt.visibility = View.VISIBLE
                      }
                      receiptAdapter?.let {
                          recyclerView_receipt.scrollToPosition(it.itemCount - 1)
                      }*/
                }

                PICK_MULTIPLE_FILE_REQUEST -> {
                    if (comeFrom == "front") {
                        if (data?.data != null) {
                            text.visibility = View.GONE
                            cnic.setImageURI(data.data)
                            //addReceiptItem(data.data)
                        } else {
                            showToast("Image not available")
                        }
                    } else if (comeFrom == "back") {
                        tt.visibility = View.GONE
                        cnicB.setImageURI(data?.data)
                    } else if (comeFrom == "STRN") {
                        str.visibility = View.GONE
                        image_Strn.setImageURI(data?.data)

                    } else if (comeFrom == "DSL") {
                        text_dsl.visibility = View.GONE
                        image_dsl.setImageURI(data?.data)
                    } else if (comeFrom == "visitingCard") {
                        text_card.visibility = View.GONE
                        image_card.setImageURI(data?.data)
                    } else if (comeFrom == "degree") {
                        text_degree.visibility = View.GONE
                        image_degree.setImageURI(data?.data)
                    }
                    /* val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                     try {
                         if (data!!.clipData != null) {
                             if (data.clipData!!.itemCount > 0) {
                                 val mClipData = data.clipData
                                 var i = 0
                                 while (i < mClipData!!.itemCount) {
                                     val item = mClipData.getItemAt(i)
                                     val uri = item.uri

                                     // Get the cursor
                                     val cursor = requireActivity().contentResolver.query(
                                         uri, filePathColumn,
                                         null, null, null
                                     )
                                     // Move to first row
                                     cursor!!.moveToFirst()
                                     val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                                     val imageEncoded = cursor.getString(columnIndex)
                                     cursor.close()
                                    // cnic.setImageURI(uri)
                                   //  addReceiptItem(uri)
                                    // i++
                                 }
                             }
                         } else if (data.data != null) {
                             cnic.setImageURI(data.data)
                             //addReceiptItem(data.data)
                         }
                     } catch (e: Exception) {
                         Log.d("Attachment-TAG", e.localizedMessage)
                     }*/
                }
                PICK_DOCUMENT -> {
                    selectedFilePath = data?.data?.let {
                        DocPathUtils.getSourceDocPath(
                            requireContext(),
                            it
                        )
                    }
                    file = File(selectedFilePath)
                    fileName = file.name
                    //docText.text = selectedFilePath.toString()
                    if (comeFrom == "selectPDF") {
                        selectedDocument(docText)
                        selectedFilePath = null
                    } else if (comeFrom == "selectOther") {
                        selectedDocument(otherDoc)
                        selectedFilePath = null
                    }
                }
            }
        }
    }

    private fun selectedDocument(v: TextView) {
        v.text = fileName
    }

  /*  private fun imageClicked(v: ImageView) {
        val compressImagePath =
            captureImageUriPath?.let { Utils.compressImage(requireContext(), it) }
        if (compressImagePath!!.isNotEmpty()) {
            captureImageFile = File(compressImagePath)
            text.visibility = View.GONE
            v.setImageBitmap(captureImageFile as Bitmap)
        }
    }*/


    private fun capturePhoto() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CODE)

    }

    private fun capturePhotoBack() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CODEE)

    }

    private fun captureStrn() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CO)
    }

    private fun requestPermission() {
        if (Utils.checkMultipleRunTimePermission(
                requireContext(), permissionList,
                REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS
            )
        ) {
            pickerActionDialog()
        } else {
            pickerActionDialog()
        }
    }

    private fun requestPermissionForSelectingDocument() {
        if (Utils.checkMultipleRunTimePermission(
                requireContext(), permissionList,
                REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS
            )
        ) {
            selectDocumentFromGallery()
        } else {
            selectDocumentFromGallery()
//            val intent = Intent(Intent.ACTION_GET_CONTENT)
//            intent.addCategory(Intent.CATEGORY_OPENABLE)
//            intent.type = "*/*"
//            startActivityForResult(Intent.createChooser(intent, "Choose File"), PICK_DOCUMENT)
        }

    }

    private fun selectDocumentFromGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "*/*"
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        startActivityForResult(Intent.createChooser(intent, "Choose File"), PICK_DOCUMENT)
    }


    @SuppressLint("QueryPermissionsNeeded")
    private fun pickerActionDialog() {

        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.custom_dialog)
        dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation_2
        dialog.window!!.setGravity(Gravity.BOTTOM)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)

        val listView = dialog.findViewById<ListView>(R.id.listView)
        val list: MutableList<GeneralBottomAdapter.ListItemModel> = ArrayList()

        list.add(GeneralBottomAdapter.ListItemModel("Gallery"))
        list.add(GeneralBottomAdapter.ListItemModel("Camera"))

        val adapter = GeneralBottomAdapter(requireContext(), "", list)
        listView.adapter = adapter
        dialog.listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val data = parent.getItemAtPosition(position)
                        as GeneralBottomAdapter.ListItemModel

                when (position) {
                    0 -> {
                        // Gallery
                        val intent: Intent = Intent(
                            Intent.ACTION_GET_CONTENT,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                        )
                        intent.type = "image/* video/*"
                        intent.putExtra(
                            Intent.EXTRA_MIME_TYPES,
                            arrayOf("image/*" /*, "video/ *"*/)
                        )
                        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                        intent.addCategory(Intent.CATEGORY_OPENABLE)
                        startActivityForResult(
                            Intent.createChooser(intent, "Select Media"),
                            PICK_MULTIPLE_FILE_REQUEST
                        )
                    }
                    1 -> {
                        // Camera
                        // use standard intent to capture an image
                        /* val chooserIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

                         // Ensure that there's a camera activity to handle the intent
                         *//*if (chooserIntent.(packageManager) != null) {*//*
                            // Create the File where the photo should go
                            try {
                                captureImageFile = createImageFile()
                            } catch (ex: IOException) {
                                // Error occurred while creating the File
                                return@OnItemClickListener
                            }
                            // Continue only if the File was successfully created
                            if (captureImageFile != null) {
                                try {
                                    val file = createImageFile()
                                    file.let {
                                        selectedImage = FileProvider.getUriForFile(
                                            requireContext(),
                                            BuildConfig.APPLICATION_ID + "provider",
                                            it
                                        )
                                    }

                                } catch (e: IOException) {
                                    e.printStackTrace()
                                }
                                chooserIntent.putExtra(MediaStore.EXTRA_OUTPUT, selectedImage)
                                startActivityForResult(chooserIntent, REQUEST_CAMERA_CAPTURE)
                            }*/
                        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        startActivityForResult(cameraIntent, REQUEST_CAMERA_CAPTURE)


                    }
                }
                dialog.dismiss()

            }
        dialog.show()

    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = File(
            Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM
            ), "Camera"
        )
        val image = File.createTempFile(
            imageFileName,  /* prefix */
            ".jpg",  /* suffix */
            storageDir /* directory */
        )

        // Save a file: path for use with ACTION_VIEW intents
        captureImageUriPath = "file:" + image.absolutePath
        return image
    }


    private fun openDocuments() {
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

    private fun getAllCustomerGroupApi(){
        val call = ServiceUtils.createService()
            .customerGroup
        //call.enqueue(object : Callback<CustomerGroupResponse>)
    }
}