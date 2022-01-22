import os
import sys

from lanzou.api import LanZouCloud

if __name__ == '__main__':
    lzy = LanZouCloud()
    cookie = {'ylogin': os.getenv('LZ_YLOGIN'), 'phpdisk_info': os.getenv('LZ_PHPDISK_INFO')}
    lzy.login_by_cookie(cookie)

    bilibili_hd_dir_id = lzy.get_dir_list().find_by_name('BilibiliHD2').id
    bilibili_hd_v01_dir_id = lzy.get_dir_list(bilibili_hd_dir_id).find_by_name('0.1').id


    def handler(fid, is_file):
        lzy.set_passwd(fid, '233', is_file)


    code = lzy.upload_file(sys.argv[1], bilibili_hd_v01_dir_id, uploaded_handler=handler)

    if code == 0:
        print('success')
    else:
        print(f'::warning :: upload to lanzou fail with code ${code}')

    exit(code)
