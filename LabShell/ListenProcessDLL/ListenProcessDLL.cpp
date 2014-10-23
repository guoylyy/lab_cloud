// ListenProcessDLL.cpp : 定义 DLL 应用程序的导出函数。
//

#include "stdafx.h"
#include <windows.h>
#include "tlhelp32.h"
#include "stdlib.h"

extern "C" __declspec(dllexport) bool getProcessStatus(char* name)
{
	HANDLE Snapshot;
	Snapshot = CreateToolhelp32Snapshot(TH32CS_SNAPPROCESS,0);
	PROCESSENTRY32 processListStr;
	processListStr.dwSize = sizeof(PROCESSENTRY32);
	BOOL return_value;
	return_value = Process32First(Snapshot,&processListStr);
	while(return_value)
	{
		size_t len = wcslen(processListStr.szExeFile)+1;
		size_t converted = 0;
		char *CStr;
		CStr=(char*)malloc(len*sizeof(char));
		wcstombs_s(&converted, CStr, len, processListStr.szExeFile, _TRUNCATE);

		if( strcmp(name, CStr) == 0 )
		{
			
			DWORD dwexitcode;
			HANDLE hwd = ::OpenProcess(PROCESS_ALL_ACCESS,FALSE,processListStr.th32ProcessID);

			WaitForSingleObject(hwd,INFINITE);

			BOOL ret = GetExitCodeProcess(hwd, &dwexitcode);

			if(ret)
			{
				return true;

			}	
			break;
		}
		return_value = Process32Next(Snapshot, &processListStr); 
	}
	CloseHandle( Snapshot );
	return false;
}


